package com.juloungjuloung.juju.dto.product

import com.juloungjuloung.juju.domain.product.impl.Ring
import java.time.LocalDateTime

data class RingDetailRes(
    val id: Long,
    val name: String,
    val productCode: String,
    val price: Long,
    val weightByMilliGram: Long,
    val thumbnailImage: String,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun of(ring: Ring): RingDetailRes {
            return RingDetailRes(
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