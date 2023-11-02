package com.juloungjuloung.juju.entity.product.impl

import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.entity.product.ProductEntity
import com.juloungjuloung.juju.product.CATEGORY_NECKLACE
import com.juloungjuloung.juju.product.ProductMaterial
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(CATEGORY_NECKLACE)
class NecklaceEntity(
    name: String,
    productCode: String,
    weightByMilliGram: Int,
    material: ProductMaterial,
    thumbnailImage: String,
    isActive: Boolean,
    @Column(name = "necklace_maximum_length")
    val maximumLength: Int,

    @Column(name = "necklace_minimum_length")
    val minimumLength: Int
) : ProductEntity(
    name = name,
    productCode = productCode,
    weightByMilliGram = weightByMilliGram,
    material = material,
    thumbnailImage = thumbnailImage,
    isActive = isActive
) {
    fun toDomain(): Necklace {
        return Necklace(
            id = this.id,
            name = this.name,
            productCode = this.productCode,
            weightByMilliGram = this.weightByMilliGram,
            material = this.material,
            thumbnailImage = this.thumbnailImage,
            isActive = this.isActive,
            maximumLength = this.maximumLength,
            minimumLength = this.minimumLength,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}