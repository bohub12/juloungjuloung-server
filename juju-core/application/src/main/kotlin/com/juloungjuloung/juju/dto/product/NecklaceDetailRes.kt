package com.juloungjuloung.juju.dto.product

import com.juloungjuloung.juju.domain.product.impl.Necklace
import java.time.LocalDateTime

data class NecklaceDetailRes(
    val id: Long,
    val name: String,
    val productCode: String,
    val price: Long,
    val weightByMilliGram: Long,
    val thumbnailImage: String,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,
    val isActive: Boolean,

    val maximumLength: Int,
    val minimumLength: Int,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun of(necklace: Necklace): NecklaceDetailRes {
            return NecklaceDetailRes(
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