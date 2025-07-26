package io.dev.globe_assessment.data.local.datasource

import io.dev.globe_assessment.data.local.dao.ProductDao
import io.dev.globe_assessment.data.local.entity.ProductEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class LocalDataSourceTest {

    private lateinit var productDao: ProductDao
    private lateinit var localDataSource: LocalDataSource

    private val dummyProducts = listOf(
        ProductEntity(1, "Product 1", "Description 1", 100.0, "thumbnail1.png"),
        ProductEntity(2, "Product 2", "Description 2", 200.0, "thumbnail2.png")
    )

    @Before
    fun setUp() {
        productDao = mock()
        localDataSource = LocalDataSource(productDao)
    }

    @Test
    fun getCachedProducts() = runTest {
        `when`(productDao.getAllProducts()).thenReturn(dummyProducts)

        val result = localDataSource.getCachedProducts()

        assertEquals(dummyProducts, result)
        verify(productDao).getAllProducts()
    }

    @Test
    fun insertProduct() = runTest {
        localDataSource.cacheProducts(dummyProducts)

        verify(productDao).clearAll()
        verify(productDao).insertAllProducts(dummyProducts)
    }

    @Test
    fun getProductById() = runTest {
        val productId = 1
        val expectedProduct = dummyProducts[0]
        `when`(productDao.getProductById(productId)).thenReturn(expectedProduct)

        val result = localDataSource.getProductById(productId)

        assertEquals(expectedProduct, result)
        verify(productDao).getProductById(productId)
    }
}
