package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.entity.BaseEntity
import com.juloungjuloung.juju.enums.ProductMaterialEnum
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class ProductMaterialEntity(
    @ManyToOne
    val product: ProductEntity,
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
) : BaseEntity()