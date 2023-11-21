package com.juloungjuloung.juju.dto.product.response.impl

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import java.time.LocalDateTime

class NecklaceResponse(
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
) : ProductResponse(
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
        fun of(necklace: Necklace): NecklaceResponse {
            return NecklaceResponse(
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