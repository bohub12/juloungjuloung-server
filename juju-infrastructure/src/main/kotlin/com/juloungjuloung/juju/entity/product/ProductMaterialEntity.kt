package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.entity.BaseEntity
import com.juloungjuloung.juju.enums.ProductMaterialEnum
import jakarta.persistence.Entity

@Entity
class ProductMaterialEntity(
    val productId: Long,
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
) : BaseEntity() {
    fun toDomain(): ProductMaterial {
        return ProductMaterial(
            id = this.id,
            productId = this.productId,
            material = this.material,
            additionalPrice = this.additionalPrice,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    companion object {
        fun of(productMaterial: ProductMaterial): ProductMaterialEntity {
            return ProductMaterialEntity(
                productId = productMaterial.productId,
                material = productMaterial.material,
                additionalPrice = productMaterial.additionalPrice
            )
        }
    }
}