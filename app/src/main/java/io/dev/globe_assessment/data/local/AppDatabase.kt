package io.dev.globe_assessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.dev.globe_assessment.data.local.dao.ProductDao
import io.dev.globe_assessment.data.local.entity.ProductEntity

/**
 * The main database definition for the application using Room.
 *
 * It contains the list of entities and serves as the access point to the underlying SQLite database.
 *
 * @property entities The list of entities associated with the database. Currently only [ProductEntity].
 * @property version The version number of the database schema.
 * @property exportSchema Whether to export the database schema to a folder. Set to false to disable schema export.
 */
@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to [ProductDao] for performing database operations on the `product_tbl`.
     */
    abstract fun productDao(): ProductDao
}
