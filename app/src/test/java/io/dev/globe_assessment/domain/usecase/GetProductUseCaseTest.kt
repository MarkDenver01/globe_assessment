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
class GetProductUseCaseTest {

    private lateinit var repository: ProductRepository
    private lateinit var useCase: GetProductUseCase

    private val fakeProducts = listOf(
        Product(
            id = 1,
            title = "Test Product 1",
            price = 100.0,
            description = "Description 1",
            thumbnail = "thumb1.jpg"
        ),
        Product(
            id = 2,
            title = "Test Product 2",
            price = 200.0,
            description = "Description 2",
            thumbnail = "thumb2.jpg"
        )
    )

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetProductUseCase(repository)
    }

    @Test
    fun success() = runTest {
        `when`(repository.getProducts()).thenReturn(fakeProducts)

        useCase().test {
            assertTrue(awaitItem() is DataResult.Loading)

            val result = awaitItem()
            assertTrue(result is DataResult.Success)
            assertEquals(fakeProducts, (result as DataResult.Success).data)

            awaitComplete()
        }
    }

    @Test
    fun fail() = runTest {
        val errorMessage = "Something went wrong"
        `when`(repository.getProducts()).thenThrow(RuntimeException(errorMessage))

        useCase().test {
            assertTrue(awaitItem() is DataResult.Loading)

            val result = awaitItem()
            assertTrue(result is DataResult.Error)
            assertEquals(errorMessage, (result as DataResult.Error).message)

            awaitComplete()
        }
    }
}
