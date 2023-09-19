package com.juloungjuloung.juju.domain.domain.product.earring

import com.juloungjuloung.juju.domain.domain.product.CATEGORY_EARRING
import com.juloungjuloung.juju.domain.domain.product.Product
import com.juloungjuloung.juju.domain.domain.product.ProductMaterial
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(CATEGORY_EARRING)
class Earring(
    name: String,
    productCode: String,
    weightByMilliGram: Int,
    material: ProductMaterial,
    thumbnailImage: String
) : Product(
    name = name,
    productCode = productCode,
    weightByMilliGram = weightByMilliGram,
    material = material,
    thumbnailImage = thumbnailImage
)