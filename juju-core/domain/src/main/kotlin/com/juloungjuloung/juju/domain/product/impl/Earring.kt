package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.product.ProductMaterial
import java.time.LocalDateTime

data class Earring(
    val id: Long?,
    val name: String,
    val productCode: String,
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
    val isActive: Boolean,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)