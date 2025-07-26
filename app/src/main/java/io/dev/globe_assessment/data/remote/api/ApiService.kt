package io.dev.globe_assessment.data.remote.api

import io.dev.globe_assessment.data.remote.response.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<ProductListResponse>
}