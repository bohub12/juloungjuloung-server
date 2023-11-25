package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

abstract class Product(
    val id: Long?,
    val type: ProductTypeEnum,
    val name: String,
    val productCode: String,
    val price: Long,
    val weightByMilliGram: Long,
    val thumbnailImage: String?,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int?,
    val isActive: Boolean,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)