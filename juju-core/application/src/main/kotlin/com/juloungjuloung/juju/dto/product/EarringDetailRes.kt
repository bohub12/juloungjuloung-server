package com.juloungjuloung.juju.dto.product

import com.juloungjuloung.juju.domain.product.impl.Earring
import java.time.LocalDateTime

data class EarringDetailRes(
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
        fun of(earring: Earring): EarringDetailRes {
            return EarringDetailRes(
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