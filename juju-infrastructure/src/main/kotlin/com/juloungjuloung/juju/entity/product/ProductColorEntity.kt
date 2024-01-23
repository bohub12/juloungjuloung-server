package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.entity.BaseEntity
import com.juloungjuloung.juju.enums.ProductColorEnum
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated

@Entity
class ProductColorEntity(
    val productId: Long,
    @Enumerated(STRING)
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
) : BaseEntity() {
    fun toDomain(): ProductColor {
        return ProductColor(
            id = id,
            productId = productId,
            color = color,
            additionalPrice = additionalPrice
        )
    }

    companion object {
        fun of(productColor: ProductColor): ProductColorEntity {
            return ProductColorEntity(
                productId = productColor.productId,
                color = productColor.color,
                additionalPrice = productColor.additionalPrice
            )
        }
    }
}