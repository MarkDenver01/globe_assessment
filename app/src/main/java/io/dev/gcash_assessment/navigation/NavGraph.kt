package io.dev.gcash_assessment.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.dev.gcash_assessment.presentation.compose.products.ProductDetailsScreen
import io.dev.gcash_assessment.presentation.compose.products.ProductListScreen

fun NavGraphBuilder.productGraph(navController: NavController) {
    composable(Routes.PRODUCT_LIST_ROUTE) {
        ProductListScreen(
            navController = navController,
            onProductClick = { productId ->
                Log.e("xxxxx", "product id: $productId")
                navController.navigate(Routes.getProductDetail(productId))
            }
        )
    }

    composable(
        route = Routes.PRODUCT_DETAIL_ROUTE,
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

fun NavGraphBuilder.favoriteGraph() {
    composable(Routes.FAVORITE_ROUTE) {
        androidx.compose.material.Text("Favorite screen")
    }
}

fun NavGraphBuilder.settingsGraph() {
    composable(Routes.SETTINGS_ROUTE) {
        androidx.compose.material.Text("Settings screen")
    }
}