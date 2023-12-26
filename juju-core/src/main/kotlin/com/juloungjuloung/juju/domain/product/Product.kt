package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

open class Product(
    val id: Long = 0L,
    val type: ProductTypeEnum,
    var name: String,
    val productCode: String,
    var price: Long,
    var weightByMilliGram: Long,
    var thumbnailImage: String?,
    var isDiamond: Boolean,
    var totalDiamondCaratX100: Int,
    var isDisplay: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    open fun update(
        name: String?,
        price: Long?,
        weightByMilliGram: Long?,
        isDiamond: Boolean?,
        totalDiamondCaratX100: Int?,
        isDisplay: Boolean?,

        braceletMaximumLength: Int?,
        braceletMinimumLength: Int?,

        necklaceMaximumLength: Int?,
        necklaceMinimumLength: Int?
    ) {
        name?.let { this.name = name }
        price?.let { this.price = price }
        weightByMilliGram?.let { this.weightByMilliGram = weightByMilliGram }
        isDiamond?.let { this.isDiamond = isDiamond }
        totalDiamondCaratX100?.let { this.totalDiamondCaratX100 = totalDiamondCaratX100 }
        isDisplay?.let { this.isDisplay = isDisplay }
    }
}