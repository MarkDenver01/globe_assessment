package io.dev.gcash_assessment.presentation.viewmodel.state

import io.dev.gcash_assessment.data.remote.response.ProductListResponse

sealed class UiState {
    object Loading : UiState()
    data class Success(val products: List<ProductListResponse>) : UiState()
    data class Error(val message: String) : UiState()
}