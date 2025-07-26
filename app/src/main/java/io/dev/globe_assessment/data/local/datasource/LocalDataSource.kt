package io.dev.globe_assessment.data.local.datasource

import io.dev.globe_assessment.data.local.dao.ProductDao
import io.dev.globe_assessment.data.local.entity.ProductEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val productDao: ProductDao
) {
    suspend fun getCachedProducts(): List<ProductEntity> =
        productDao.getAllProducts()

    suspend fun cacheProducts(products: List<ProductEntity>) {
        productDao.clearAll()
        productDao.insertAllProducts(products)
    }

    suspend fun getProductById(productId: Int): ProductEntity {
        return productDao.getProductById(productId)
    }
}