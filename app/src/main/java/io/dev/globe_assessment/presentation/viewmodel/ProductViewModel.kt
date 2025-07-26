package io.dev.globe_assessment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.usecase.GetProductByIdUseCase
import io.dev.globe_assessment.domain.usecase.GetProductUseCase
import io.dev.globe_assessment.domain.util.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {
    private val _productListState = MutableStateFlow<DataResult<List<Product>>>(DataResult.Loading)
    val productListState: StateFlow<DataResult<List<Product>>> = _productListState.asStateFlow()

    private val _productDetailState = MutableStateFlow<DataResult<Product>>(DataResult.Loading)
    val productDetailState: StateFlow<DataResult<Product>> = _productDetailState.asStateFlow()

    init {
        fetchAllProducts()
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            getProductUseCase.invoke().collect { result ->
                _productListState.value = result
            }
        }
    }

    fun fetchProductDetail(productId: Int) {
        viewModelScope.launch {
            getProductByIdUseCase(productId).collect { result ->
                _productDetailState.value = result
            }
        }
    }
}