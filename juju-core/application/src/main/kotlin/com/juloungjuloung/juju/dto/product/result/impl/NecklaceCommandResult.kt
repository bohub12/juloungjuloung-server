package com.juloungjuloung.juju.dto.product.result.impl

import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

class NecklaceCommandResult(
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
    updatedAt: LocalDateTime,

    val maximumLength: Int,
    val minimumLength: Int
) : ProductCommandResult(
    id = id,
    type = ProductTypeEnum.NECKLACE.name,
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
        fun of(necklace: Necklace): NecklaceCommandResult {
            return NecklaceCommandResult(
                id = necklace.id!!,
                name = necklace.name,
                productCode = necklace.productCode,
                price = necklace.price,
                weightByMilliGram = necklace.weightByMilliGram,
                thumbnailImage = necklace.thumbnailImage,
                isDiamond = necklace.isDiamond,
                totalDiamondCaratX100 = necklace.totalDiamondCaratX100 ?: 0,
                isActive = necklace.isActive,
                maximumLength = necklace.maximumLength,
                minimumLength = necklace.minimumLength,
                createdAt = necklace.createdAt!!,
                updatedAt = necklace.updatedAt!!
            )
        }
    }
}