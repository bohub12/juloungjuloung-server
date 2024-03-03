package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.domain.product.ProductOption
import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.Entity

@Entity
class ProductOptionEntity(
    val productOptionCategoryId: Long,
    val name: String,
    val additionalPrice: Long = 0
) : BaseEntity() {
    fun toDomain(): ProductOption {
        return ProductOption(
            id = id,
            productOptionCategoryId = productOptionCategoryId,
            name = name,
            additionalPrice = additionalPrice
        )
    }

    companion object {
        fun of(productOption: ProductOption): ProductOptionEntity {
            return ProductOptionEntity(
                productOptionCategoryId = productOption.productOptionCategoryId,
                name = productOption.name,
                additionalPrice = productOption.additionalPrice
            )
        }
    }
}