package io.dev.gcash_assessment.presentation.compose


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import io.dev.gcash_assessment.domain.util.DataResult
import io.dev.gcash_assessment.navigation.Routes
import io.dev.gcash_assessment.presentation.viewmodel.ProductViewModel
import io.dev.gcash_assessment.presentation.viewmodel.state.UiState

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Navigation observer
    LaunchedEffect(Unit) {
        viewModel.navigateTo.collect {
            navController.navigate(Routes.PRODUCT_LIST_ROUTE) {
                popUpTo(Routes.PRODUCT_DETAIL_ROUTE) { inclusive = true }
            }
        }
    }

    when(state) {
        is DataResult.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is DataResult.Error -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (state as DataResult.Error).message)
            }
        }

        is DataResult.Success -> {
            val products = (state as DataResult.Success).data
            LazyColumn {
                items(products) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = 4.dp
                    ) {
                        Row(Modifier.padding(8.dp)) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(product.thumbnail)
                                        .crossfade(true)
                                        .build()
                                ),
                                contentDescription = product.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(64.dp)
                                    .padding(end = 8.dp)
                            )
                            Column {
                                Text(
                                    text = product.title,
                                    style = MaterialTheme.typography.headlineLarge
                                )
                                Text(
                                    text = "$${product.price}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}