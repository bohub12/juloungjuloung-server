package com.juloungjuloung.juju.dto.product.response

import com.juloungjuloung.juju.enums.ProductColorEnum

data class ProductColorResponse(
    val id: Long,
    val productId: Long,
    val color: ProductColorEnum,
    val additionalPrice: Int
)