package io.dev.globe_assessment.data.repository

import android.util.Log
import io.dev.globe_assessment.data.local.datasource.LocalDataSource
import io.dev.globe_assessment.data.mapper.toDomain
import io.dev.globe_assessment.data.mapper.toEntity
import io.dev.globe_assessment.data.remote.datasource.RemoteDataSource
import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Implementation of [ProductRepository] that coordinates between
 * remote and local data sources to fetch product information.
 */
class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : ProductRepository {

    /**
     * Fetches a list of products from the remote data source.
     * Falls back to local cache in case of an exception.
     */
    override suspend fun getProducts(): List<Product> {
        return try {
            val products = remoteDataSource.fetchProducts()
            localDataSource.cacheProducts(products.map { it.toEntity() })
            products.map { it.toDomain() }
        } catch (e: Exception) {
            // Fallback to local cache
            val cached = localDataSource.getCachedProducts()
            cached.map { it.toDomain() }
        }
    }

    /**
     * Fetches a product by its ID from the local data source.
     */
    override suspend fun getProductById(productId: Int): Product {
        return localDataSource.getProductById(productId).toDomain()
    }
}
