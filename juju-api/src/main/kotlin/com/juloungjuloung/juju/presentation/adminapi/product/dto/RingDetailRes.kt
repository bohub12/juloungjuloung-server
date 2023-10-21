package com.juloungjuloung.juju.presentation.adminapi.product.dto

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.domain.product.ring.Ring
import java.time.LocalDateTime

data class RingDetailRes(
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
        fun of(ring: Ring): RingDetailRes {
            return RingDetailRes(
                id = ring.id!!,
                name = ring.name,
                productCode = ring.productCode,
                weightByMilliGram = ring.weightByMilliGram,
                material = ring.material,
                thumbnailImage = ring.thumbnailImage,
                isActive = ring.isActive,
                createdAt = ring.createdAt,
                updatedAt = ring.updatedAt
            )
        }
    }
}