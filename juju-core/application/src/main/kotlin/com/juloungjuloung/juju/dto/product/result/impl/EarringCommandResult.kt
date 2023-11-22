package com.juloungjuloung.juju.dto.product.result.impl

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import java.time.LocalDateTime

class EarringCommandResult(
    id: Long,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String?,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int,
    isActive: Boolean,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime
) : ProductCommandResult(
    id = id,
    type = ProductTypeEnum.EARRING.name,
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
    companion object {
        fun of(earring: Earring): EarringCommandResult {
            return EarringCommandResult(
                id = earring.id!!,
                name = earring.name,
                productCode = earring.productCode,
                price = earring.price,
                weightByMilliGram = earring.weightByMilliGram,
                thumbnailImage = earring.thumbnailImage,
                isDiamond = earring.isDiamond,
                totalDiamondCaratX100 = earring.totalDiamondCaratX100 ?: 0,
                isActive = earring.isActive,
                createdAt = earring.createdAt!!,
                updatedAt = earring.updatedAt!!
            )
        }
    }
}