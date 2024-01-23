package com.juloungjuloung.juju.domain.productcolor

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.enums.ProductColorEnum
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productColorFixture(
    id: Long = 1L,
    productId: Long = 1L,
    color: ProductColorEnum = ProductColorEnum.GOLD,
    additionalPrice: Int = 0
): ProductColor {
    return fixtureMonkey.giveMeBuilder<ProductColor>()
        .setExp(ProductColor::id, id)
        .setExp(ProductColor::productId, productId)
        .setExp(ProductColor::color, color)
        .setExp(ProductColor::additionalPrice, additionalPrice)
        .sample()
}

fun productColorCollectionFixture(
    ids: List<Long> = listOf(1L, 2L),
    productId: Long = 1L,
    colors: List<ProductColorEnum> = listOf(
        ProductColorEnum.GOLD,
        ProductColorEnum.ROSE_GOLD
    ),
    additionalPrices: Array<Int> = arrayOf(0, 1000)
): List<ProductColor> {
    return ids.mapIndexed { index, it ->
        productColorFixture(
            id = it,
            productId = productId,
            color = colors[index],
            additionalPrice = additionalPrices[index]
        )
    }
}