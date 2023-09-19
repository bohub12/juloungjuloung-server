package com.juloungjuloung.juju.domain.domain.product

import com.juloungjuloung.juju.domain.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class ProductImage(
    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,
    val imageUrl: String,
    val isPrimary: Boolean
) : BaseEntity()