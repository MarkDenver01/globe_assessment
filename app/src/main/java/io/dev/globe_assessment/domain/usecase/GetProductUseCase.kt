package io.dev.globe_assessment.domain.usecase

import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.repository.ProductRepository
import io.dev.globe_assessment.domain.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for retrieving a list of all products.
 *
 * @property repository The repository that provides access to product data.
 */
class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    /**
     * Invokes the use case to fetch all products.
     *
     * Emits a [DataResult.Loading] state first,
     * then either [DataResult.Success] with the list of products
     * or [DataResult.Error] with an error message.
     *
     * @return A [Flow] emitting the loading, success, or error state.
     */
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
