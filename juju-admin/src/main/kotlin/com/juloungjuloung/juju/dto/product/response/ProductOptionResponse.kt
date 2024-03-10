package com.juloungjuloung.juju.dto.product.response

data class ProductOptionResponse(
    val id: Long,
    var productOptionCategoryId: Long,
    val name: String,
    var additionalPrice: Long
)