package io.dev.globe_assessment.domain.usecase

import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.repository.ProductRepository
import io.dev.globe_assessment.domain.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(productId: Int): Flow<DataResult<Product>> = flow {
        emit(DataResult.Loading)
        try {
            val product = repository.getProductById(productId)
            emit(DataResult.Success(product))
        } catch (e: Exception) {
            emit(DataResult.Error(e.message ?: "Unknown error"))
        }
    }
}