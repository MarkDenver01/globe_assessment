package io.dev.globe_assessment.presentation.compose.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.dev.globe_assessment.domain.util.DataResult
import io.dev.globe_assessment.presentation.compose.products.components.BottomNavigationBar
import io.dev.globe_assessment.presentation.compose.products.components.ProductGrid
import io.dev.globe_assessment.presentation.compose.products.components.TopBar
import io.dev.globe_assessment.presentation.viewmodel.ProductViewModel

/**
 * Product list screen displaying a grid of products.
 * It shows a loading spinner, error message, or product grid based on the state.
 *
 * @param navController Navigation controller used to handle navigation.
 * @param viewModel Injected ViewModel to fetch product list state.
 * @param onProductClick Callback when a product is clicked.
 */
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    // Collect UI state from ViewModel
    val state by viewModel.productListState.collectAsState()

    // Scaffold layout with top and bottom bars
    Scaffold(
        topBar = {
            TopBar(
                title = "Products",
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier.navigationBarsPadding()
            )
        }
    ) { padding ->
        // Content of the screen
        Box(
            modifier = Modifier
                .background(Color(0xFFF0FFF4))
                .fillMaxSize()
                .padding(padding)
        ) {
            // Render based on state (Loading, Error, Success)
            when (val result = state) {
                is DataResult.Loading -> {
                    // Show loading spinner
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is DataResult.Error -> {
                    // Show error message
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = (state as DataResult.Error).message)
                    }
                }

                is DataResult.Success -> {
                    // Show grid of products
                    ProductGrid(
                        products = result.data,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}
