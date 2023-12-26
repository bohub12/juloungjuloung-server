package com.juloungjuloung.juju.dto.product.response.impl

import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import java.time.LocalDateTime

class EarringResponse(
    id: Long,
    type: String,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String?,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int,
    isDisplay: Boolean,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime
) : ProductResponse(
    id = id,
    type = type,
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
    companion object {
        fun of(result: Earring): EarringResponse {
            return EarringResponse(
                id = result.id,
                type = result.type.name,
                name = result.name,
                productCode = result.productCode,
                price = result.price,
                weightByMilliGram = result.weightByMilliGram,
                thumbnailImage = result.thumbnailImage,
                isDiamond = result.isDiamond,
                totalDiamondCaratX100 = result.totalDiamondCaratX100,
                isDisplay = result.isDisplay,
                createdAt = result.createdAt,
                updatedAt = result.updatedAt
            )
        }
    }
}