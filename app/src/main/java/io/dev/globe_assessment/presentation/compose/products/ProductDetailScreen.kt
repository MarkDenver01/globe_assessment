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

@Composable
fun ProductDetailsScreen(
    productId: Int,
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
    onBack: () -> Unit = { navController.popBackStack() }
) {
    val productResult by viewModel.productDetailState.collectAsState()

    LaunchedEffect(productId) {
        viewModel.fetchProductDetail(productId)
    }

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
                    ProductDetailsContent(product = result.data)
                }
            }
        }
    }
}
