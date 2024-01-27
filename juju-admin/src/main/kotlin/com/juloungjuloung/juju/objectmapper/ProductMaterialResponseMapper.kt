package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.dto.product.response.ProductMaterialResponse

fun toResponse(productMaterial: ProductMaterial): ProductMaterialResponse {
    return ProductMaterialResponse(
        id = productMaterial.id,
        productId = productMaterial.productId,
        material = productMaterial.material,
        additionalPrice = productMaterial.additionalPrice
    )
}