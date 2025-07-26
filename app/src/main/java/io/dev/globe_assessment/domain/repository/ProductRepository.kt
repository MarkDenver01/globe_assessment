package io.dev.globe_assessment.domain.repository

import io.dev.globe_assessment.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>

    suspend fun getProductById(productId: Int): Product
}