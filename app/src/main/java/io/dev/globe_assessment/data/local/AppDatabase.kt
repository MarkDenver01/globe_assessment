package io.dev.globe_assessment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.dev.globe_assessment.data.local.dao.ProductDao
import io.dev.globe_assessment.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}