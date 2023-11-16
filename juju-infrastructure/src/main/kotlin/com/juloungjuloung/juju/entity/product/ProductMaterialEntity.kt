package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.common.constant.ProductMaterialEnum
import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class ProductMaterialEntity(
    @ManyToOne
    val product: ProductEntity,
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
) : BaseEntity()