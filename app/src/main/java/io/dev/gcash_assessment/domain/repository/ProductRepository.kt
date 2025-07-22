package io.dev.gcash_assessment.domain.repository

import io.dev.gcash_assessment.data.remote.response.ProductListResponse
import io.dev.gcash_assessment.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}