package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_SIZE_EXCEED_MAX
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_THUMBNAIL_NOT_ONE
import java.time.LocalDateTime

data class ProductImage(
    val id: Long = 0L,
    val productId: Long,
    val imageUrl: String,
    var isThumbnail: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

fun List<ProductImage>.combineForValidation(productImages: List<ProductImage>): ProductImages {
    return ProductImages(this + productImages)
}

fun List<ProductImage>.containsThumbnail(): Boolean {
    return this.any { it.isThumbnail }
}

fun List<ProductImage>.getThumbnail(): ProductImage {
    return this.first { it.isThumbnail }
}

fun List<ProductImage>.getNonThumbnails(): List<ProductImage> {
    return this.filter { !it.isThumbnail }
}

fun List<ProductImage>.changeThumbnail(productThumbnailImageId: Long) {
    this.filter { it.id != productThumbnailImageId }.map { it.isThumbnail = false }
    this.filter { it.id == productThumbnailImageId }.map { it.isThumbnail = true }
}

data class ProductImages(
    val productImages: List<ProductImage>
) {
    init {
        requireProperties()
    }

    private fun requireProperties() {
        if (productImages.size >= 10) {
            throw BusinessLogicException(PRODUCT_IMAGE_SIZE_EXCEED_MAX)
        }

        if (productImages.filter { it.isThumbnail }.size != 1) {
            throw BusinessLogicException(PRODUCT_IMAGE_THUMBNAIL_NOT_ONE)
        }
    }
}