package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.entity.product.impl.BraceletEntity
import com.juloungjuloung.juju.product.ProductMaterial
import java.time.LocalDateTime

data class Bracelet(
    val id: Long?,
    val name: String,
    val productCode: String,
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
    val isActive: Boolean,
    val maximumLength: Int,
    val minimumLength: Int,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    companion object {
        fun of(entity: BraceletEntity): Bracelet {
            return Bracelet(
                id = entity.id,
                name = entity.name,
                productCode = entity.productCode,
                weightByMilliGram = entity.weightByMilliGram,
                material = entity.material,
                thumbnailImage = entity.thumbnailImage,
                isActive = entity.isActive,
                maximumLength = entity.maximumLength,
                minimumLength = entity.minimumLength,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }
}