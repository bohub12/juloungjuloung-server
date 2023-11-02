package com.juloungjuloung.juju.dto.product

import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.product.ProductMaterial
import java.time.LocalDateTime

data class EarringDetailRes(
    val id: Long,
    val name: String,
    val productCode: String,
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
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
                weightByMilliGram = earring.weightByMilliGram,
                material = earring.material,
                thumbnailImage = earring.thumbnailImage,
                isActive = earring.isActive,
                createdAt = earring.createdAt!!,
                updatedAt = earring.updatedAt!!
            )
        }
    }
}