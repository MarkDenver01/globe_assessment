package io.dev.globe_assessment.data.remote.datasource

import io.dev.globe_assessment.data.remote.api.ApiService
import io.dev.globe_assessment.data.remote.response.ProductDto
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Responsible for fetching product data from the remote API.
 *
 * @property api The Retrofit API service used to make network requests.
 */
class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {

    /**
     * Fetches a list of products from the remote API.
     *
     * @return A list of [ProductDto] representing the raw product data.
     * @throws HttpException if the HTTP response is not successful.
     * @throws Exception if the response body is null.
     */
    suspend fun fetchProducts(): List<ProductDto> {
        val response = api.getProducts()

        if (!response.isSuccessful) {
            throw HttpException(response)
        }

        val responseBody = response.body()
        if (responseBody == null) {
            throw Exception("Response body is empty")
        }

        return responseBody.products
    }
}
