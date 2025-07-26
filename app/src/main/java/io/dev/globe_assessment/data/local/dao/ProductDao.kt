package io.dev.globe_assessment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.dev.globe_assessment.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_tbl")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM product_tbl WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(products: List<ProductEntity>)

    @Query("DELETE FROM product_tbl")
    suspend fun clearAll()
}