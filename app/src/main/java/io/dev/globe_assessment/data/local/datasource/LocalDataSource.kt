package io.dev.globe_assessment.data.local.datasource

import io.dev.globe_assessment.data.local.dao.ProductDao
import io.dev.globe_assessment.data.local.entity.ProductEntity
import javax.inject.Inject

/**
 * Local data source responsible for accessing and managing cached product data
 * from the local Room database via [ProductDao].
 *
 * @property productDao Data Access Object for performing product-related database operations.
 */
class LocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {

    /**
     * Retrieves all cached products from the local database.
     *
     * @return A list of cached [ProductEntity] objects.
     */
    suspend fun getCachedProducts(): List<ProductEntity> =
        productDao.getAllProducts()

    /**
     * Caches the provided list of products into the local database.
     * Existing product records will be cleared before inserting new data.
     *
     * @param products A list of [ProductEntity] to be cached locally.
     */
    suspend fun cacheProducts(products: List<ProductEntity>) {
        productDao.clearAll()
        productDao.insertAllProducts(products)
    }

    /**
     * Retrieves a specific product by its ID from the local cache.
     *
     * @param productId The ID of the product to fetch.
     * @return The matching [ProductEntity] if found in the local cache.
     */
    suspend fun getProductById(productId: Int): ProductEntity {
        return productDao.getProductById(productId)
    }
}
