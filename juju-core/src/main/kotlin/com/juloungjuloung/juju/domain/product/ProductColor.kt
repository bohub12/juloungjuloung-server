package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.enums.ProductColorEnum
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_COLOR_DUPLICATE_CODE_IN_SAME_PRODUCT

data class ProductColor(
    val id: Long = 0L,
    val productId: Long,
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
) {
    init {
        require(additionalPrice >= 0)
    }
}

fun List<ProductColor>.combineForValidation(productColors: List<ProductColor>): ProductColors {
    return ProductColors(this + productColors)
}

data class ProductColors(
    val productColors: List<ProductColor>
) {
    init {
        requireNoDuplicateColors(productColors)
    }

    private fun requireNoDuplicateColors(productColors: List<ProductColor>) {
        val uniqueColors = productColors.map(ProductColor::color).toSet()

        if (productColors.size != uniqueColors.size) {
            throw BusinessLogicException(PRODUCT_COLOR_DUPLICATE_CODE_IN_SAME_PRODUCT)
        }
    }
}