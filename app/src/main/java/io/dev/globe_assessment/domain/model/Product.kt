package io.dev.globe_assessment.domain.model

/**
 * Domain model representing a product in the app.
 * This is used by the business logic layer.
 */
data class Product(
    val id: Int,             // Unique identifier for the product
    val title: String,       // Product name/title
    val description: String, // Detailed description of the product
    val price: Double,       // Product price
    val thumbnail: String    // URL of the product thumbnail image
)
