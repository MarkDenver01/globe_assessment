package io.dev.gcash_assessment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.dev.gcash_assessment.domain.model.Product
import io.dev.gcash_assessment.domain.repository.ProductRepository
import io.dev.gcash_assessment.domain.usecase.ProductUseCase
import io.dev.gcash_assessment.domain.util.DataResult
import io.dev.gcash_assessment.presentation.viewmodel.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _navigateTo = MutableStateFlow<String?>(null)
    val navigateTo = _navigateTo.asStateFlow()

    private val _state = MutableStateFlow<DataResult<List<Product>>>(DataResult.Loading)
    val state: StateFlow<DataResult<List<Product>>> = _state.asStateFlow()


    init {
        fetchAllProducts()
    }


    private fun fetchAllProducts() {
        viewModelScope.launch {
            productUseCase.invoke().collect { result ->
                _state.value = result
            }
        }
    }

    fun resetNavigation() {
        _navigateTo.value = null
    }
}