package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.domain.product.ProductOptionCategory
import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.Entity

@Entity
class ProductOptionCategoryEntity(
    val productId: Long,
    val name: String
) : BaseEntity() {
    fun toDomain(): ProductOptionCategory {
        return ProductOptionCategory(
            id = id,
            productId = productId,
            name = name
        )
    }

    companion object {
        fun of(productOptionCategory: ProductOptionCategory): ProductOptionCategoryEntity {
            return ProductOptionCategoryEntity(
                productId = productOptionCategory.productId,
                name = productOptionCategory.name
            )
        }
    }
}