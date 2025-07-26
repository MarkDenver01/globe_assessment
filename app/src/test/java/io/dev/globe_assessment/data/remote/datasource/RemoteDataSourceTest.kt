package io.dev.globe_assessment.data.remote.datasource

import io.dev.globe_assessment.data.remote.api.ApiService
import io.dev.globe_assessment.data.remote.response.ProductDto
import io.dev.globe_assessment.data.remote.response.ProductListResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceTest {

    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource

    private val fakeDto = ProductDto(
        id = 1,
        title = "Product 1",
        description = "Description 1",
        price = 100.0,
        thumbnail = "https://example.com/image1.jpg"
    )

    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java)
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun fetchProductsSuccess() = runTest {
        val response = Response.success(ProductListResponse(listOf(fakeDto)))
        `when`(apiService.getProducts()).thenReturn(response)

        val result = remoteDataSource.fetchProducts()

        assertEquals(1, result.size)
        assertEquals(fakeDto, result.first())
        verify(apiService).getProducts()
    }

    @Test(expected = HttpException::class)
    fun fetchProductsThrowsException() = runTest {
        val errorResponse = Response.error<ProductListResponse>(
            404,
            "Not Found".toResponseBody("application/json".toMediaTypeOrNull())
        )
        `when`(apiService.getProducts()).thenReturn(errorResponse)

        remoteDataSource.fetchProducts()
    }

    @Test(expected = Exception::class)
    fun fetchProductsNull() = runTest {
        val response = Response.success(null)
        `when`(apiService.getProducts()).thenReturn(response.body())

        remoteDataSource.fetchProducts()
    }
}
