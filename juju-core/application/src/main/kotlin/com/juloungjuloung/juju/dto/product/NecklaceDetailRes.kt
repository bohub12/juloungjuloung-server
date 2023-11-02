package com.juloungjuloung.juju.dto.product

import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.product.ProductMaterial
import java.time.LocalDateTime

data class NecklaceDetailRes(
    val id: Long,
    val name: String,
    val productCode: String,
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
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
                weightByMilliGram = necklace.weightByMilliGram,
                material = necklace.material,
                thumbnailImage = necklace.thumbnailImage,
                isActive = necklace.isActive,
                maximumLength = necklace.maximumLength,
                minimumLength = necklace.minimumLength,
                createdAt = necklace.createdAt!!,
                updatedAt = necklace.updatedAt!!
            )
        }
    }
}