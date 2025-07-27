package io.dev.globe_assessment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a product entity stored in the local Room database.
 *
 * This entity is mapped to the table named `product_tbl`.
 *
 * @property id The unique identifier of the product. Acts as the primary key.
 * @property title The title or name of the product.
 * @property description A brief description of the product.
 * @property price The price of the product.
 * @property thumbnail A URL or path to the thumbnail image of the product.
 */
@Entity(tableName = "product_tbl")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)
