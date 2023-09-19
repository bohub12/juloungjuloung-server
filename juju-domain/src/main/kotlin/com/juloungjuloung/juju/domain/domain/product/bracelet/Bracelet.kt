package com.juloungjuloung.juju.domain.domain.product.bracelet

import com.juloungjuloung.juju.domain.domain.product.CATEGORY_BRACELET
import com.juloungjuloung.juju.domain.domain.product.Product
import com.juloungjuloung.juju.domain.domain.product.ProductMaterial
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(CATEGORY_BRACELET)
class Bracelet(
    name: String,
    productCode: String,
    weightByMilliGram: Int,
    material: ProductMaterial,
    thumbnailImage: String,

    @Column(name = "bracelet_maximum_length")
    val maximumLength: Int,

    @Column(name = "bracelet_minimum_length")
    val minimumLength: Int
) : Product(
    name = name,
    productCode = productCode,
    weightByMilliGram = weightByMilliGram,
    material = material,
    thumbnailImage = thumbnailImage
)