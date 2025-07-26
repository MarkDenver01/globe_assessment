package io.dev.globe_assessment.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_tbl")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)