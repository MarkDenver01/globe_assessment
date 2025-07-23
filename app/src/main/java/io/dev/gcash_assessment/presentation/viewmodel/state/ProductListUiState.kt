package io.dev.gcash_assessment.presentation.viewmodel.state

import io.dev.gcash_assessment.data.remote.response.ProductListResponse
import io.dev.gcash_assessment.domain.model.Product

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)