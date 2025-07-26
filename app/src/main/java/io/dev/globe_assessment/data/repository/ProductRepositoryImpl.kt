package io.dev.globe_assessment.data.repository

import android.util.Log
import io.dev.globe_assessment.data.local.datasource.LocalDataSource
import io.dev.globe_assessment.data.mapper.toDomain
import io.dev.globe_assessment.data.mapper.toEntity
import io.dev.globe_assessment.data.remote.datasource.RemoteDataSource
import io.dev.globe_assessment.domain.model.Product
import io.dev.globe_assessment.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return try {
            val products = remoteDataSource.fetchProducts()
            localDataSource.cacheProducts(products.map { it.toEntity() })
            products.map { it.toDomain() }
        } catch (e: Exception) {
            Log.e("DENVER", "error: $e")
            // Fallback to local cache
            val cached = localDataSource.getCachedProducts()
            cached.map { it.toDomain() }
        }
    }

    override suspend fun getProductById(productId: Int): Product {
        return localDataSource.getProductById(productId).toDomain()
    }
}