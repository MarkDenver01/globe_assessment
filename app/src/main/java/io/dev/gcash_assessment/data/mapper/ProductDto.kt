package io.dev.gcash_assessment.data.mapper

import io.dev.gcash_assessment.data.local.entity.ProductEntity
import io.dev.gcash_assessment.data.remote.response.ProductDto
import io.dev.gcash_assessment.data.remote.response.ProductListResponse
import io.dev.gcash_assessment.domain.model.Product


fun ProductDto.toEntity(): ProductEntity = ProductEntity(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail
)

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail
)

fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    price = price,
    thumbnail = thumbnail
)