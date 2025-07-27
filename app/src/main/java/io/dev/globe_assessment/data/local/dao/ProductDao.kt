package io.dev.globe_assessment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.dev.globe_assessment.data.local.entity.ProductEntity

/**
 * Data Access Object (DAO) for interacting with the product table in the local Room database.
 */
@Dao
interface ProductDao {

    /**
     * Retrieves all products from the local database.
     *
     * @return A list of all [ProductEntity] records.
     */
    @Query("SELECT * FROM product_tbl")
    suspend fun getAllProducts(): List<ProductEntity>

    /**
     * Retrieves a single product from the database by its unique ID.
     *
     * @param productId The ID of the product to fetch.
     * @return The matching [ProductEntity], if found.
     */
    @Query("SELECT * FROM product_tbl WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: Int): ProductEntity

    /**
     * Inserts a list of products into the database.
     * If a product already exists, it will be replaced.
     *
     * @param products A list of [ProductEntity] to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(products: List<ProductEntity>)

    /**
     * Deletes all products from the local database.
     */
    @Query("DELETE FROM product_tbl")
    suspend fun clearAll()
}
