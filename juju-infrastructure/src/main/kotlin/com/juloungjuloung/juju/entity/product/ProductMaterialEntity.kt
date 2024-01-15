package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.entity.BaseEntity
import com.juloungjuloung.juju.enums.ProductMaterialEnum
import jakarta.persistence.Entity

@Entity
class ProductMaterialEntity(
    val productId: Long,
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
) : BaseEntity()