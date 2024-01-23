package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.dto.product.response.ProductColorResponse

fun toResponse(productColor: ProductColor): ProductColorResponse {
    return ProductColorResponse(
        id = productColor.id,
        productId = productColor.productId,
        color = productColor.color,
        additionalPrice = productColor.additionalPrice
    )
}