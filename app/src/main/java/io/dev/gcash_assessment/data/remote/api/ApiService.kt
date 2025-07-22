package io.dev.gcash_assessment.data.remote.api

import androidx.room.Query
import io.dev.gcash_assessment.data.remote.response.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products?limit=10")
    suspend fun getProducts(): Response<ProductListResponse>
}