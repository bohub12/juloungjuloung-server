package com.juloungjuloung.juju.domain.productimage

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productImageFixture(
    isThumbnail: Boolean = false,
    id: Long = 1L,
    productId: Long = 1L
): ProductImage {
    return fixtureMonkey.giveMeBuilder<ProductImage>()
        .setExp(ProductImage::id, id)
        .setExp(ProductImage::productId, productId)
        .setExp(ProductImage::isThumbnail, isThumbnail)
        .sample()
}

fun productImageCollectionFixture(
    thumbnailId: Long = 1L,
    notThumbnailIds: List<Long> = listOf(2L, 3L),
    productId: Long = 1L
): List<ProductImage> {
    return listOf(productImageFixture(isThumbnail = true, id = thumbnailId, productId = productId)) +
        notThumbnailIds.map { productImageFixture(isThumbnail = false, id = it, productId = productId) }
}