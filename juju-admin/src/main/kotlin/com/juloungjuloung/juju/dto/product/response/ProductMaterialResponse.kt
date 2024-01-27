package com.juloungjuloung.juju.dto.product.response

import com.juloungjuloung.juju.enums.ProductMaterialEnum

data class ProductMaterialResponse(
    val id: Long,
    val productId: Long,
    val material: ProductMaterialEnum,
    val additionalPrice: Int
)