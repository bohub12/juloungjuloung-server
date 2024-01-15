package com.juloungjuloung.juju.domain.productImage

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productImageFixture(isPrimary: Boolean = false, id: Long = 1L, productId: Long = 1L): ProductImage {
    return fixtureMonkey.giveMeBuilder<ProductImage>()
        .setExp(ProductImage::id, id)
        .setExp(ProductImage::productId, productId)
        .sample()
}

fun productImageCollectionFixture(): List<ProductImage> {
    return listOf(
        productImageFixture(isPrimary = true, id = 1),
        productImageFixture(isPrimary = false, id = 2),
        productImageFixture(isPrimary = false, id = 3)
    )
}