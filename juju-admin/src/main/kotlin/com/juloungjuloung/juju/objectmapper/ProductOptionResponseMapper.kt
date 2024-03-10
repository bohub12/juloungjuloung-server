package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.ProductOption
import com.juloungjuloung.juju.domain.product.ProductOptionCategory
import com.juloungjuloung.juju.domain.product.ProductOptionInfo
import com.juloungjuloung.juju.dto.product.response.ProductOptionCategoryResponse
import com.juloungjuloung.juju.dto.product.response.ProductOptionInfoResponse
import com.juloungjuloung.juju.dto.product.response.ProductOptionResponse

fun toResponse(productOptionInfo: ProductOptionInfo): ProductOptionInfoResponse {
    return ProductOptionInfoResponse(
        optionCategory = toResponse(productOptionInfo.optionCategory),
        options = productOptionInfo.options.options.map { toResponse(it) }
    )
}

fun toResponse(productOptionCategory: ProductOptionCategory): ProductOptionCategoryResponse {
    return ProductOptionCategoryResponse(
        id = productOptionCategory.id,
        productId = productOptionCategory.productId,
        name = productOptionCategory.name
    )
}

fun toResponse(productOption: ProductOption): ProductOptionResponse {
    return ProductOptionResponse(
        id = productOption.id,
        productOptionCategoryId = productOption.productOptionCategoryId,
        name = productOption.name,
        additionalPrice = productOption.additionalPrice
    )
}