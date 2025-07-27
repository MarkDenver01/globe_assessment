package io.dev.globe_assessment.domain.repository

import io.dev.globe_assessment.domain.model.Product

/**
 * Repository interface for accessing product-related data.
 * Abstracts the data source (network, database, etc.) from the domain layer.
 */
interface ProductRepository {

    /**
     * Fetches a list of all products.
     *
     * @return List of [Product] objects.
     */
    suspend fun getProducts(): List<Product>

    /**
     * Fetches a single product by its unique identifier.
     *
     * @param productId ID of the product to fetch.
     * @return The [Product] corresponding to the given ID.
     */
    suspend fun getProductById(productId: Int): Product
}
