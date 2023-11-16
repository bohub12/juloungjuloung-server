package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.entity.BaseEntity
import com.juloungjuloung.juju.product.ProductColorEnum
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class ProductColorEntity(
    @ManyToOne
    val product: ProductEntity,
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
) : BaseEntity()