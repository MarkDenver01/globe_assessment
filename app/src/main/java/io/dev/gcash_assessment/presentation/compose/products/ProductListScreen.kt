package io.dev.gcash_assessment.presentation.compose.products

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
import io.dev.gcash_assessment.domain.model.Product
import io.dev.gcash_assessment.domain.util.DataResult
import io.dev.gcash_assessment.navigation.Routes
import io.dev.gcash_assessment.presentation.compose.components.BottomNavigationBar
import io.dev.gcash_assessment.presentation.compose.components.TopBar
import io.dev.gcash_assessment.presentation.compose.products.components.ProductGrid
import io.dev.gcash_assessment.presentation.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

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
        Box(
            modifier = Modifier
                .background(Color(0xFFF0FFF4))
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val result = state) {
                is DataResult.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is DataResult.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = (state as DataResult.Error).message)
                    }
                }

                is DataResult.Success -> {
                    ProductGrid(
                        products = result.data,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}
