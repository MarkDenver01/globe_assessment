package io.dev.gcash_assessment.data.remote.datasource

import io.dev.gcash_assessment.data.remote.api.ApiService
import io.dev.gcash_assessment.data.remote.response.ProductDto
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {

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