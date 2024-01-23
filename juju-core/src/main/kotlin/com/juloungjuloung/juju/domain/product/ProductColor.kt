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