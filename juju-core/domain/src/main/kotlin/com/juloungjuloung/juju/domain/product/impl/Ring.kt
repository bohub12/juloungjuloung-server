package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.entity.product.impl.RingEntity
import com.juloungjuloung.juju.product.ProductMaterial
import java.time.LocalDateTime

data class Ring(
    val id: Long?,
    val name: String,
    val productCode: String,
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
    val isActive: Boolean,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    companion object {
        fun of(entity: RingEntity): Ring {
            return Ring(
                id = entity.id,
                name = entity.name,
                productCode = entity.productCode,
                weightByMilliGram = entity.weightByMilliGram,
                material = entity.material,
                thumbnailImage = entity.thumbnailImage,
                isActive = entity.isActive,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }
}