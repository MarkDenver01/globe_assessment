package io.dev.gcash_assessment.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.dev.gcash_assessment.presentation.compose.ProductDetailsScreen
import io.dev.gcash_assessment.presentation.compose.ProductListScreen

fun NavGraphBuilder.productListGraph(navController: NavController) {
    composable(Routes.PRODUCT_LIST_ROUTE) {
        ProductListScreen(navController)
    }
}

fun NavGraphBuilder.productDetailGraph(navController: NavController) {
    composable(Routes.PRODUCT_DETAIL_ROUTE) {
        ProductDetailsScreen(navController)
    }
}