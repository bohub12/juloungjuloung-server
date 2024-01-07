package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class ProductImageEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    val product: ProductEntity,
    val imageUrl: String,
    val isPrimary: Boolean
) : BaseEntity() {

    fun toDomain(): ProductImage {
        return ProductImage(
            id = this.id,
            product = product.toDomain(),
            imageUrl = this.imageUrl,
            isPrimary = this.isPrimary,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    companion object {
        fun of(productImage: ProductImage): ProductImageEntity {
            return ProductImageEntity(
                product = ProductEntity.of(productImage.product),
                imageUrl = productImage.imageUrl,
                isPrimary = productImage.isPrimary
            )
        }
    }
}