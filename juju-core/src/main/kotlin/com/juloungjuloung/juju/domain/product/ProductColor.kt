package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.enums.ProductColorEnum

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
        require(productColors.size <= ProductColorEnum.entries.size)
        requireNoDuplicateColors(productColors)
    }

    private fun requireNoDuplicateColors(productColors: List<ProductColor>) {
        val uniqueColors = productColors.map(ProductColor::color).toSet()
        require(productColors.size == uniqueColors.size) { "Duplicate colors" }
    }
}