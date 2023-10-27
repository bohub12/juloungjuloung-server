package com.juloungjuloung.juju.entity.product.impl

import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.entity.product.ProductEntity
import com.juloungjuloung.juju.product.CATEGORY_RING
import com.juloungjuloung.juju.product.ProductMaterial
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(CATEGORY_RING)
class RingEntity(
    name: String,
    productCode: String,
    weightByMilliGram: Int,
    material: ProductMaterial,
    thumbnailImage: String,
    isActive: Boolean
) : ProductEntity(
    name = name,
    productCode = productCode,
    weightByMilliGram = weightByMilliGram,
    material = material,
    thumbnailImage = thumbnailImage,
    isActive = isActive
) {
    fun toDomain(): Ring {
        return Ring(
            id = this.id,
            name = this.name,
            productCode = this.productCode,
            weightByMilliGram = this.weightByMilliGram,
            material = this.material,
            thumbnailImage = this.thumbnailImage,
            isActive = this.isActive,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}