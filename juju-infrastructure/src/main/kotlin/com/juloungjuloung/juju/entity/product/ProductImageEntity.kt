package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class ProductImageEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    val product: ProductEntity,
    val imageUrl: String,
    val isPrimary: Boolean
) : BaseEntity()