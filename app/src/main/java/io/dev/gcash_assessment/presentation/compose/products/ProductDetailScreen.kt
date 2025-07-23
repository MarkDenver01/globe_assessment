package io.dev.gcash_assessment.presentation.compose.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.dev.gcash_assessment.domain.util.DataResult
import io.dev.gcash_assessment.presentation.compose.components.BottomNavigationBar
import io.dev.gcash_assessment.presentation.compose.components.TopBar
import io.dev.gcash_assessment.presentation.compose.products.components.ProductDetailsContent
import io.dev.gcash_assessment.presentation.viewmodel.ProductViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    productId: Int?,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val product = viewModel.fetchProductById(productId ?: -1)

    Scaffold(
        topBar = {
            TopBar(
                title = "Product Details",
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
                .background(Color(0xFFF5F5F5))
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ProductDetailsContent(product)
            }
        }
    }
}