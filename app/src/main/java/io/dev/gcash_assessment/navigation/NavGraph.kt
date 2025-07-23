package io.dev.gcash_assessment.navigation

import androidx.compose.material.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.dev.gcash_assessment.presentation.compose.products.ProductDetailsScreen
import io.dev.gcash_assessment.presentation.compose.products.ProductListScreen

fun NavGraphBuilder.productListGraph(navController: NavController) {
    composable(Routes.PRODUCT_LIST_ROUTE) {
        ProductListScreen(
            navController = navController,
            onProductClick = { productId ->
                navController.navigate(Routes.getProductDetail(productId))
            }
        )
    }

    composable(
        route = "${Routes.PRODUCT_DETAIL_ROUTE}/{productId}",
        arguments = listOf(navArgument("productId") {
            type = NavType.IntType
        })
    ) { backStackEntry ->
        val productId = backStackEntry.arguments?.getInt("productId")
        ProductDetailsScreen(
            navController = navController,
            productId = productId
        )
    }
}

fun NavGraphBuilder.productDetailGraph(navController: NavController) {
    composable(
        route = Routes.PRODUCT_DETAIL_ROUTE,
        arguments = listOf(navArgument("productId") { type = NavType.IntType })
    ) {
        val productId = it.arguments?.getInt("productId")
        ProductDetailsScreen(navController, productId)
    }
}



fun NavGraphBuilder.favoriteGraph() { // TODO add navController param, if screen is existing
    composable(Routes.FAVORITE_ROUTE) {
        Text("Favorite screen") // TODO you can implement fav screen
    }
}

fun NavGraphBuilder.settingsGraph() { // TODO add navController param, if screen is existing
    composable(Routes.SETTINGS_ROUTE) {
        Text("Settings screen") // TODO you can implement settings screen
    }
}