package io.dev.globe_assessment.data.remote.api

import io.dev.globe_assessment.data.remote.response.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API interface for accessing remote product data.
 */
interface ApiService {

    /**
     * Fetches the list of products from the remote API.
     *
     * @return A [Response] wrapping [ProductListResponse] which contains a list of products.
     */
    @GET("products")
    suspend fun getProducts(): Response<ProductListResponse>
}
