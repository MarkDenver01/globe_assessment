package io.dev.globe_assessment.presentation.viewmodel

import app.cash.turbine.test
import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.usecase.GetProductByIdUseCase
import io.dev.globe_assessment.domain.usecase.GetProductUseCase
import io.dev.globe_assessment.domain.util.DataResult
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @Mock
    private lateinit var getProductUseCase: GetProductUseCase

    @Mock
    private lateinit var getProductByIdUseCase: GetProductByIdUseCase

    private lateinit var viewModel: ProductViewModel
    private val testDispatcher = StandardTestDispatcher()

    private val fakeProducts = listOf(
        Product(
            id = 1,
            title = "iPhone 15",
            description = "Latest Apple iPhone",
            price = 1099.99,
            thumbnail = "https://example.com/iphone15.jpg"
        )
    )

    private val fakeProduct = Product(
        id = 1,
        title = "iPhone 15",
        description = "Latest Apple iPhone",
        price = 1099.99,
        thumbnail = "https://example.com/iphone15.jpg"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchAllProducts_loading_then_success() = runTest {
        // Given
        `when`(getProductUseCase()).thenReturn(
            flow {
                emit(DataResult.Loading)
                emit(DataResult.Success(fakeProducts))
            }
        )

        // When
        viewModel = ProductViewModel(getProductUseCase, getProductByIdUseCase)

        // Then
        viewModel.productListState.test {
            assertTrue(awaitItem() is DataResult.Loading)
            val success = awaitItem()
            assertTrue(success is DataResult.Success)
            assertEquals(fakeProducts, (success as DataResult.Success).data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun fetchAllProducts_emit_errors() = runTest {
        val errorMessage = "Network error"
        `when`(getProductUseCase()).thenReturn(
            flow {
                emit(DataResult.Loading)
                emit(DataResult.Error(errorMessage))
            }
        )

        viewModel = ProductViewModel(getProductUseCase, getProductByIdUseCase)

        viewModel.productListState.test {
            assertTrue(awaitItem() is DataResult.Loading)
            val error = awaitItem()
            assertTrue(error is DataResult.Error)
            assertEquals(errorMessage, (error as DataResult.Error).message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun fetchProductDetail_loading_then_success() = runTest {
        `when`(getProductUseCase()).thenReturn(flowOf(DataResult.Success(emptyList())))
        `when`(getProductByIdUseCase(1)).thenReturn(
            flow {
                emit(DataResult.Loading)
                emit(DataResult.Success(fakeProduct))
            }
        )

        viewModel = ProductViewModel(getProductUseCase, getProductByIdUseCase)
        viewModel.fetchProductDetail(1)

        viewModel.productDetailState.test {
            assertTrue(awaitItem() is DataResult.Loading)
            val result = awaitItem()
            assertTrue(result is DataResult.Success)
            assertEquals(fakeProduct, (result as DataResult.Success).data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun fetchProductDetail_emit_error() = runTest {
        val errorMessage = "Product not found"
        `when`(getProductUseCase()).thenReturn(flowOf(DataResult.Success(emptyList())))
        `when`(getProductByIdUseCase(999)).thenReturn(
            flow {
                emit(DataResult.Loading)
                emit(DataResult.Error(errorMessage))
            }
        )

        viewModel = ProductViewModel(getProductUseCase, getProductByIdUseCase)
        viewModel.fetchProductDetail(999)

        viewModel.productDetailState.test {
            assertTrue(awaitItem() is DataResult.Loading)
            val error = awaitItem()
            assertTrue(error is DataResult.Error)
            assertEquals(errorMessage, (error as DataResult.Error).message)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
