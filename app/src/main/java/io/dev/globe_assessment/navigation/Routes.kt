package io.dev.globe_assessment.navigation

object Routes {
    const val PRODUCT_LIST_ROUTE = "product_list_route"
    const val FAVORITE_ROUTE = "favorite_route"
    const val SETTINGS_ROUTE = "settings_route"
    const val PRODUCT_DETAIL_BASE = "product_detail_route"
    const val PRODUCT_DETAIL_ROUTE = "$PRODUCT_DETAIL_BASE/{productId}"

    fun getProductDetail(productId: Int) = "$PRODUCT_DETAIL_BASE/$productId"


}