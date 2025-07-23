package io.dev.gcash_assessment.presentation.compose.products.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.dev.gcash_assessment.domain.model.Product

@Composable
fun ProductDetailsContent(product: Product?) {
    if (product == null) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Product not found.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = product.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Description: ${product.description}")
            Text(text = "Price: $${product.price}")
        }
    }
}
