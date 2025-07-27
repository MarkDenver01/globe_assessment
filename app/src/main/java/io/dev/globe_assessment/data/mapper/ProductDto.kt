package io.dev.globe_assessment.data.mapper

import io.dev.globe_assessment.data.local.entity.ProductEntity
import io.dev.globe_assessment.data.remote.response.ProductDto
import io.dev.globe_assessment.domain.model.Product

/**
 * Maps [ProductDto] from the remote API to [ProductEntity] for local database storage.
 *
 * @receiver The remote data transfer object representing the product.
 * @return A [ProductEntity] suitable for caching in Room.
 */
fun ProductDto.toEntity(): ProductEntity = ProductEntity(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail
)

/**
 * Maps [ProductEntity] from the local database to the domain model [Product].
 *
 * @receiver The local database entity representing the product.
 * @return A [Product] model used within the app’s domain layer.
 */
fun ProductEntity.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail
)

/**
 * Maps [ProductDto] directly to the domain model [Product].
 *
 * Useful for cases where local caching is not required.
 *
 * @receiver The remote data transfer object representing the product.
 * @return A [Product] model used within the app’s domain layer.
 */
fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail
)
