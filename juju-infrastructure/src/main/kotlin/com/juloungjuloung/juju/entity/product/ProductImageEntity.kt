package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.Entity

@Entity
class ProductImageEntity(
    val productId: Long,
    val imageUrl: String,
    val isThumbnail: Boolean
) : BaseEntity() {

    fun toDomain(): ProductImage {
        return ProductImage(
            id = this.id,
            productId = this.productId,
            imageUrl = this.imageUrl,
            isThumbnail = this.isThumbnail,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    companion object {
        fun of(productImage: ProductImage): ProductImageEntity {
            return ProductImageEntity(
                productId = productImage.productId,
                imageUrl = productImage.imageUrl,
                isThumbnail = productImage.isThumbnail
            )
        }
    }
}