package com.juloungjuloung.juju.domain.product

import java.time.LocalDateTime

data class Product(
    val id: Long?,
    // Product Category TODO : Enum or String?
    val category: String,
    val name: String,
    val productCode: String,
    val weightByMilliGram: Long,
    val thumbnailImage: String,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int?,
    val isActive: Boolean,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)