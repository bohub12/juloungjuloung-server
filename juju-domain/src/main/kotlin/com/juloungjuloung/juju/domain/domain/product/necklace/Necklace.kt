package com.juloungjuloung.juju.domain.domain.product.necklace

import com.juloungjuloung.juju.domain.domain.product.CATEGORY_NECKLACE
import com.juloungjuloung.juju.domain.domain.product.Product
import com.juloungjuloung.juju.domain.domain.product.ProductMaterial
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(CATEGORY_NECKLACE)
class Necklace(
    name: String,
    productCode: String,
    weightByMilliGram: Int,
    material: ProductMaterial,
    thumbnailImage: String,

    @Column(name = "necklace_maximum_length")
    val maximumLength: Int,

    @Column(name = "necklace_minimum_length")
    val minimumLength: Int
) : Product(
    name = name,
    productCode = productCode,
    weightByMilliGram = weightByMilliGram,
    material = material,
    thumbnailImage = thumbnailImage
)