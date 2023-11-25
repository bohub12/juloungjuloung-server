package com.juloungjuloung.juju.dto.product.result.impl

import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

class RingCommandResult(
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
        fun of(ring: Ring): RingCommandResult {
            return RingCommandResult(
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