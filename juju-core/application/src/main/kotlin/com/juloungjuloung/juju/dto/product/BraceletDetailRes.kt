package com.juloungjuloung.juju.dto.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import java.time.LocalDateTime

data class BraceletDetailRes(
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
        fun of(bracelet: Bracelet): BraceletDetailRes {
            return BraceletDetailRes(
                id = bracelet.id!!,
                name = bracelet.name,
                productCode = bracelet.productCode,
                price = bracelet.price,
                weightByMilliGram = bracelet.weightByMilliGram,
                thumbnailImage = bracelet.thumbnailImage,
                isDiamond = bracelet.isDiamond,
                totalDiamondCaratX100 = bracelet.totalDiamondCaratX100 ?: 0,
                isActive = bracelet.isActive,
                maximumLength = bracelet.maximumLength,
                minimumLength = bracelet.minimumLength,
                createdAt = bracelet.createdAt!!,
                updatedAt = bracelet.updatedAt!!
            )
        }
    }
}