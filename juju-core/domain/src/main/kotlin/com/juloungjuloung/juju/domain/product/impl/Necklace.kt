package com.juloungjuloung.juju.domain.product.impl

import java.time.LocalDateTime

data class Necklace(
    val id: Long?,
    val name: String,
    val productCode: String,
    val price: Long,
    val weightByMilliGram: Long,
    val thumbnailImage: String,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int?,
    val isActive: Boolean,

    val maximumLength: Int,
    val minimumLength: Int,

    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)