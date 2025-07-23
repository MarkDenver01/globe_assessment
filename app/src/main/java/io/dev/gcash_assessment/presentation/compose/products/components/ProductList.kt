package io.dev.gcash_assessment.presentation.compose.products.components

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import io.dev.gcash_assessment.domain.model.Product
import io.dev.gcash_assessment.navigation.Routes

@Composable
fun ProductList(
    products: List<Product>,
    onProductClick: (Int) -> Unit
) {
    LazyColumn {
        items(products) { product ->
            ProductCard(
                product = product,
                onClick = {
                    onProductClick(product.id)
                }
            )
        }
    }
}