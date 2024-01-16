package com.juloungjuloung.juju.domain.productImage

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productImageFixture(isPrimary: Boolean = false, id: Long = 1L, productId: Long = 1L): ProductImage {
    return fixtureMonkey.giveMeBuilder<ProductImage>()
        .setExp(ProductImage::id, id)
        .setExp(ProductImage::productId, productId)
        .setExp(ProductImage::isPrimary, isPrimary)
        .sample()
}

fun productImageCollectionFixture(
    primaryId: Long = 1L,
    notPrimaryIds: List<Long> = listOf(2L, 3L),
    productId: Long = 1L
): List<ProductImage> {
    return listOf(productImageFixture(isPrimary = true, id = primaryId, productId = productId)) +
        notPrimaryIds.map { productImageFixture(isPrimary = false, id = it, productId = productId) }
}