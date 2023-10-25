package com.juloungjuloung.juju.presentation.adminapi.product.dto

import com.juloungjuloung.juju.domain.product.ProductMaterial
import java.time.LocalDateTime

data class NecklaceDetailRes(
    val id: Long,
    val name: String,
    val productCode: String,
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
    val isActive: Boolean,

    val maximumLength: Int,
    val minimumLength: Int,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)