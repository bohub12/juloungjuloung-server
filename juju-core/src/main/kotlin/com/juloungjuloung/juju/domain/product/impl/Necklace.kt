package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

class Necklace(
    id: Long = 0L,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String?,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int,
    isDisplay: Boolean,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now(),

    var maximumLength: Int,
    var minimumLength: Int
) : Product(
    id = id,
    type = ProductTypeEnum.NECKLACE,
    name = name,
    productCode = productCode,
    price = price,
    weightByMilliGram = weightByMilliGram,
    thumbnailImage = thumbnailImage,
    isDiamond = isDiamond,
    totalDiamondCaratX100 = totalDiamondCaratX100,
    isDisplay = isDisplay,
    createdAt = createdAt,
    updatedAt = updatedAt
) {
    override fun update(
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
        super.update(
            name,
            price,
            weightByMilliGram,
            isDiamond,
            totalDiamondCaratX100,
            isDisplay,
            braceletMaximumLength,
            braceletMinimumLength,
            necklaceMaximumLength,
            necklaceMinimumLength
        )
        necklaceMaximumLength?.let { this.maximumLength = necklaceMaximumLength }
        necklaceMinimumLength?.let { this.minimumLength = necklaceMinimumLength }
    }
}