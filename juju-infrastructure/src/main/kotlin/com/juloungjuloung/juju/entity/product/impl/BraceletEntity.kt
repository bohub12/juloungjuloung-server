package com.juloungjuloung.juju.entity.product.impl

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.entity.product.ProductEntity
import com.juloungjuloung.juju.product.CATEGORY_BRACELET
import com.juloungjuloung.juju.product.ProductMaterial
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(CATEGORY_BRACELET)
class BraceletEntity(
    name: String,
    productCode: String,
    weightByMilliGram: Int,
    material: ProductMaterial,
    thumbnailImage: String,
    isActive: Boolean,

    @Column(name = "bracelet_maximum_length")
    val maximumLength: Int,

    @Column(name = "bracelet_minimum_length")
    val minimumLength: Int
) : ProductEntity(
    name = name,
    productCode = productCode,
    weightByMilliGram = weightByMilliGram,
    material = material,
    thumbnailImage = thumbnailImage,
    isActive = isActive
) {

    fun toDomain(): Bracelet {
        return Bracelet(
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