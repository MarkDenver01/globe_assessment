package io.dev.globe_assessment.domain.usecase

import app.cash.turbine.test
import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.repository.ProductRepository
import io.dev.globe_assessment.domain.util.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GetProductByIdUseCaseTest {

    private lateinit var repository: ProductRepository
    private lateinit var useCase: GetProductByIdUseCase

    private val fakeProduct = Product(
        id = 1,
        title = "Test Product",
        price = 100.0,
        description = "Test Description",
        thumbnail = "test.jpg"
    )

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetProductByIdUseCase(repository)
    }

    @Test
    fun success() = runTest {
        val productId = 1
        `when`(repository.getProductById(productId)).thenReturn(fakeProduct)

        useCase(productId).test {
            assertTrue(awaitItem() is DataResult.Loading)

            val emission = awaitItem()
            assertTrue(emission is DataResult.Success)
            assertEquals(fakeProduct, (emission as DataResult.Success).data)

            awaitComplete()
        }
    }

    @Test
    fun fail() = runTest {
        val productId = 99
        val errorMessage = "Product not found"
        `when`(repository.getProductById(productId)).thenThrow(RuntimeException(errorMessage))

        useCase(productId).test {
            assertTrue(awaitItem() is DataResult.Loading)

            val emission = awaitItem()
            assertTrue(emission is DataResult.Error)
            assertEquals(errorMessage, (emission as DataResult.Error).message)

            awaitComplete()
        }
    }
}
