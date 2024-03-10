package com.juloungjuloung.juju.dto.product.response

data class ProductOptionInfoResponse(
    val optionCategory: ProductOptionCategoryResponse,
    val options: List<ProductOptionResponse>
)