package io.dev.globe_assessment.data.repository

import io.dev.globe_assessment.data.local.datasource.LocalDataSource
import io.dev.globe_assessment.data.local.entity.ProductEntity
import io.dev.globe_assessment.data.mapper.toDomain
import io.dev.globe_assessment.data.mapper.toEntity
import io.dev.globe_assessment.data.remote.datasource.RemoteDataSource
import io.dev.globe_assessment.data.remote.response.ProductDto
import io.dev.globe_assessment.domain.model.Product
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var repository: ProductRepositoryImpl

    private val fakeDto = ProductDto(
        id = 1,
        title = "iPhone 15",
        description = "Latest Apple iPhone",
        price = 1099.99,
        thumbnail = "https://example.com/iphone15.jpg"
    )

    private val fakeEntity = ProductEntity(
        id = 1,
        title = "iPhone 15",
        description = "Latest Apple iPhone",
        price = 1099.99,
        thumbnail = "https://example.com/iphone15.jpg"
    )

    private val fakeDomain = Product(
        id = 1,
        title = "iPhone 15",
        description = "Latest Apple iPhone",
        price = 1099.99,
        thumbnail = "https://example.com/iphone15.jpg"
    )

    @Before
    fun setUp() {
        remoteDataSource = mock(RemoteDataSource::class.java)
        localDataSource = mock(LocalDataSource::class.java)
        repository = ProductRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun getProductsReturnDataFromRemoteAndCacheLocally() = runTest {
        // Given
        val remoteList = listOf(fakeDto)
        val entityList = listOf(fakeDto.toEntity())
        val domainList = listOf(fakeDto.toDomain())

        `when`(remoteDataSource.fetchProducts()).thenReturn(remoteList)

        // When
        val result = repository.getProducts()

        // Then
        verify(remoteDataSource).fetchProducts()
        verify(localDataSource).cacheProducts(entityList)
        assertEquals(domainList, result)
    }

    @Test
    fun getProductsFallbackToLocalCacheWhenRemoteFails() = runTest {
        // Given
        val cachedList = listOf(fakeEntity)
        val expected = cachedList.map { it.toDomain() }

        `when`(remoteDataSource.fetchProducts()).thenThrow(RuntimeException("Network error"))
        `when`(localDataSource.getCachedProducts()).thenReturn(cachedList)

        // When
        val result = repository.getProducts()

        // Then
        verify(remoteDataSource).fetchProducts()
        verify(localDataSource).getCachedProducts()
        assertEquals(expected, result)
    }

    @Test
    fun getProductByIdReturnDomainModelFromLocal() = runTest {
        // Given
        `when`(localDataSource.getProductById(1)).thenReturn(fakeEntity)

        // When
        val result = repository.getProductById(1)

        // Then
        verify(localDataSource).getProductById(1)
        assertEquals(fakeEntity.toDomain(), result)
    }
}
