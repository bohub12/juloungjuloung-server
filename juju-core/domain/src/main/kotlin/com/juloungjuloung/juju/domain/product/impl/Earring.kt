package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

class Earring(
    id: Long? = null,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String? = null,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int?,
    isActive: Boolean = false,
    createdAt: LocalDateTime? = null,
    updatedAt: LocalDateTime? = null
) : Product(
    id = id,
    type = ProductTypeEnum.EARRING,
    name = name,
    productCode = productCode,
    price = price,
    weightByMilliGram = weightByMilliGram,
    thumbnailImage = thumbnailImage,
    isDiamond = isDiamond,
    totalDiamondCaratX100 = totalDiamondCaratX100,
    isActive = isActive,
    createdAt = createdAt,
    updatedAt = updatedAt
) {
    override fun update(
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
    ) {
        name?.let { this.name = name }
        price?.let { this.price = price }
        weightByMilliGram?.let { this.weightByMilliGram = weightByMilliGram }
        isDiamond?.let { this.isDiamond = isDiamond }
        totalDiamondCaratX100?.let { this.totalDiamondCaratX100 = totalDiamondCaratX100 }
        isActive?.let { this.isActive = isActive }
    }
}