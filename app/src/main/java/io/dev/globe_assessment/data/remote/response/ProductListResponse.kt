package io.dev.globe_assessment.data.remote.response


/**
 * API response.
 */
data class ProductListResponse(
    val products: List<ProductDto>
)

/**
 * Product Data transfer object used by network layer.
 */
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)
