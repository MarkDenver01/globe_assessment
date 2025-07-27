package io.dev.globe_assessment.domain.usecase

import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.repository.ProductRepository
import io.dev.globe_assessment.domain.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for retrieving a single product by its ID.
 *
 * @property repository The repository that provides access to product data.
 */
class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    /**
     * Invokes the use case to fetch a product by ID.
     *
     * Emits a [DataResult.Loading] state first,
     * then either [DataResult.Success] with the product data
     * or [DataResult.Error] with the error message.
     *
     * @param productId The ID of the product to retrieve.
     * @return A [Flow] emitting the loading, success, or error state.
     */
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
