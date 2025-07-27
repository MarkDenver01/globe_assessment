package io.dev.globe_assessment.navigation

/**
 * Defines all navigation routes used in the app.
 */
object Routes {
    // Route to the product list screen
    const val PRODUCT_LIST_ROUTE = "product_list_route"

    // Route to the favorites screen
    const val FAVORITE_ROUTE = "favorite_route"

    // Route to the settings screen
    const val SETTINGS_ROUTE = "settings_route"

    // Base and full route for the product detail screen with a dynamic productId
    const val PRODUCT_DETAIL_BASE = "product_detail_route"
    const val PRODUCT_DETAIL_ROUTE = "$PRODUCT_DETAIL_BASE/{productId}"

    /**
     * Helper to create the product detail route with a specific productId.
     */
    fun getProductDetail(productId: Int) = "$PRODUCT_DETAIL_BASE/$productId"
}
