package io.dev.globe_assessment.data.remote.response

/**
 * Represents the response from the API when fetching a list of products.
 *
 * @property products The list of [ProductDto] received from the API.
 */
data class ProductListResponse(
    val products: List<ProductDto>
)

/**
 * Data Transfer Object (DTO) that represents a product received from the API.
 *
 * @property id The unique identifier of the product.
 * @property title The name or title of the product.
 * @property description A detailed description of the product.
 * @property price The price of the product.
 * @property thumbnail The URL of the product's thumbnail image.
 */
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)
