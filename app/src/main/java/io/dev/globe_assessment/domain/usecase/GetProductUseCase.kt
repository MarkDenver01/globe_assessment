package io.dev.globe_assessment.domain.usecase

import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.repository.ProductRepository
import io.dev.globe_assessment.domain.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<DataResult<List<Product>>> = flow {
        emit(DataResult.Loading)
        try {
            val products = repository.getProducts()
            emit(DataResult.Success(products))
        } catch (e: Exception) {
            emit(DataResult.Error(e.message ?: "Unexpected error"))
        }
    }
}