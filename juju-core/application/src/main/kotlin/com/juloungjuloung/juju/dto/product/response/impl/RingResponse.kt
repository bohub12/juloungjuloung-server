package com.juloungjuloung.juju.dto.product.response.impl

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import java.time.LocalDateTime

class RingResponse(
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
) : ProductResponse(
    id = id,
    type = ProductTypeEnum.RING.name,
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
        fun of(ring: Ring): RingResponse {
            return RingResponse(
                id = ring.id!!,
                name = ring.name,
                productCode = ring.productCode,
                price = ring.price,
                weightByMilliGram = ring.weightByMilliGram,
                thumbnailImage = ring.thumbnailImage,
                isDiamond = ring.isDiamond,
                totalDiamondCaratX100 = ring.totalDiamondCaratX100 ?: 0,
                isActive = ring.isActive,
                createdAt = ring.createdAt!!,
                updatedAt = ring.updatedAt!!
            )
        }
    }
}