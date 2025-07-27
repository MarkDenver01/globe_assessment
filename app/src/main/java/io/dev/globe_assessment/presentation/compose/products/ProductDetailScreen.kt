package io.dev.globe_assessment.presentation.compose.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.dev.globe_assessment.domain.util.DataResult
import io.dev.globe_assessment.presentation.compose.products.components.BottomNavigationBar
import io.dev.globe_assessment.presentation.compose.products.components.TopBar
import io.dev.globe_assessment.presentation.viewmodel.ProductViewModel
import io.dev.globe_assessment.presentation.compose.products.components.ProductDetailsContent

/**
 * Composable screen for displaying product details.
 *
 * @param productId ID of the product to be displayed
 * @param navController Navigation controller for navigating between screens
 * @param viewModel Injected ViewModel for handling product state
 * @param onBack Callback for handling back navigation
 */
@Composable
fun ProductDetailsScreen(
    productId: Int,
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
    onBack: () -> Unit = { navController.popBackStack() }
) {
    // Observes the UI state for the selected product
    val productResult by viewModel.productDetailState.collectAsState()

    // Fetch the product detail when the composable is first launched or when productId changes
    LaunchedEffect(productId) {
        viewModel.fetchProductDetail(productId)
    }

    // Scaffold layout with a top bar and bottom navigation
    Scaffold(
        topBar = {
            TopBar(
                title = "Product Details",
                modifier = Modifier.statusBarsPadding(),
                onBack = onBack
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            // Handles different states: Loading, Error, and Success
            when (val result = productResult) {
                is DataResult.Loading -> {
                    CircularProgressIndicator()
                }
                is DataResult.Error -> {
                    Text(
                        text = "Error: ${result.message}",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                is DataResult.Success -> {
                    // Displays the product details content
                    ProductDetailsContent(product = result.data)
                }
            }
        }
    }
}
