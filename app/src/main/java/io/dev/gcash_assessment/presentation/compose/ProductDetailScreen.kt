package io.dev.gcash_assessment.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.dev.gcash_assessment.presentation.viewmodel.ProductViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val navigateTo by viewModel.navigateTo.collectAsState()
    LaunchedEffect(navigateTo) {
        navigateTo?.let { route ->
            navController.navigate(route)
            viewModel.resetNavigation()
        }
    }
}