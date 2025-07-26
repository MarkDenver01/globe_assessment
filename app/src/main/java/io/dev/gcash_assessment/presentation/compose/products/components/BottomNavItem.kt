package io.dev.gcash_assessment.presentation.compose.products.components

import androidx.annotation.DrawableRes
import io.dev.gcash_assessment.R
import io.dev.gcash_assessment.navigation.Routes

sealed class BottomNavItem(
    val route: String,
    val title: String, // TODO if screen needed, changed this
    @DrawableRes val icon: Int
) {
    object Products : BottomNavItem(
        route = Routes.PRODUCT_LIST_ROUTE,
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Favorites : BottomNavItem(
        Routes.FAVORITE_ROUTE,
        title = "Favorites",
        icon = R.drawable.ic_favorite
    )

    object Settings : BottomNavItem(
        Routes.SETTINGS_ROUTE,
        title = "Settings",
        icon = R.drawable.ic_settings
    )

    companion object {
        val items = listOf(Products, Favorites, Settings)
    }
}