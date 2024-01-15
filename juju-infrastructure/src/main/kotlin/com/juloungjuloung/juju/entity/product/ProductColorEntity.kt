package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.entity.BaseEntity
import com.juloungjuloung.juju.enums.ProductColorEnum
import jakarta.persistence.Entity

@Entity
class ProductColorEntity(
    val productId: Long,
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
) : BaseEntity()