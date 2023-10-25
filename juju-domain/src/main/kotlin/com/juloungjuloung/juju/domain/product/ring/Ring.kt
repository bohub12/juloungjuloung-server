package com.juloungjuloung.juju.domain.product.ring

import com.juloungjuloung.juju.domain.product.CATEGORY_RING
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductMaterial
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(CATEGORY_RING)
class Ring(
    name: String,
    productCode: String,
    weightByMilliGram: Int,
    material: ProductMaterial,
    thumbnailImage: String,
    isActive: Boolean
) : Product(
    name = name,
    productCode = productCode,
    weightByMilliGram = weightByMilliGram,
    material = material,
    thumbnailImage = thumbnailImage,
    isActive = isActive
)