package com.juloungjuloung.juju.dto.product.response.impl

import com.juloungjuloung.juju.dto.product.response.ProductResponse
import com.juloungjuloung.juju.dto.product.result.impl.NecklaceCommandResult
import java.time.LocalDateTime

class NecklaceResponse(
    id: Long,
    type: String,
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
    type = type,
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
        fun of(result: NecklaceCommandResult): NecklaceResponse {
            return NecklaceResponse(
                id = result.id,
                type = result.type,
                name = result.name,
                productCode = result.productCode,
                price = result.price,
                weightByMilliGram = result.weightByMilliGram,
                thumbnailImage = result.thumbnailImage,
                isDiamond = result.isDiamond,
                totalDiamondCaratX100 = result.totalDiamondCaratX100,
                isActive = result.isActive,
                maximumLength = result.maximumLength,
                minimumLength = result.minimumLength,
                createdAt = result.createdAt,
                updatedAt = result.updatedAt
            )
        }
    }
}