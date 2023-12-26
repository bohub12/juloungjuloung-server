package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

abstract class Product(
    val id: Long?,
    val type: ProductTypeEnum,
    var name: String,
    val productCode: String,
    var price: Long,
    var weightByMilliGram: Long,
    var thumbnailImage: String?,
    var isDiamond: Boolean,
    var totalDiamondCaratX100: Int?,
    var isActive: Boolean,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    abstract fun update(
        name: String?,
        price: Long?,
        weightByMilliGram: Long?,
        isDiamond: Boolean?,
        totalDiamondCaratX100: Int?,
        isActive: Boolean?,

        braceletMaximumLength: Int?,
        braceletMinimumLength: Int?,

        necklaceMaximumLength: Int?,
        necklaceMinimumLength: Int?
    )
}