package com.juloungjuloung.juju.dto.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.product.ProductMaterial
import java.time.LocalDateTime

data class BraceletDetailRes(
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
        fun of(bracelet: Bracelet): BraceletDetailRes {
            return BraceletDetailRes(
                id = bracelet.id!!,
                name = bracelet.name,
                productCode = bracelet.productCode,
                weightByMilliGram = bracelet.weightByMilliGram,
                material = bracelet.material,
                thumbnailImage = bracelet.thumbnailImage,
                isActive = bracelet.isActive,
                maximumLength = bracelet.maximumLength,
                minimumLength = bracelet.minimumLength,
                createdAt = bracelet.createdAt!!,
                updatedAt = bracelet.updatedAt!!
            )
        }
    }
}