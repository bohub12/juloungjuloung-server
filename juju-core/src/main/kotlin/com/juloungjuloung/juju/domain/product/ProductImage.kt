package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_PRIMARY_NOT_ONE
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_SIZE_EXCEED_MAX
import java.time.LocalDateTime

data class ProductImage(
    val id: Long = 0L,
    val productId: Long,
    val imageUrl: String,
    var isPrimary: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

fun List<ProductImage>.add(productImages: List<ProductImage>): ProductImages {
    return ProductImages(this + productImages)
}

fun List<ProductImage>.containsPrimary(): Boolean {
    return this.any { it.isPrimary }
}

fun List<ProductImage>.getPrimary(): ProductImage {
    return this.first { it.isPrimary }
}

fun List<ProductImage>.getNonPrimary(): List<ProductImage> {
    return this.filter { !it.isPrimary }
}

fun List<ProductImage>.changePrimary(primaryProductImageId: Long) {
    this.filter { it.id != primaryProductImageId }.map { it.isPrimary = false }
    this.filter { it.id == primaryProductImageId }.map { it.isPrimary = true }
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

        if (productImages.filter { it.isPrimary }.size != 1) {
            throw BusinessLogicException(PRODUCT_IMAGE_PRIMARY_NOT_ONE)
        }
    }

    fun getPrimaryImage(): ProductImage {
        return productImages.first { it.isPrimary }
    }

    fun filterNotSaved(): List<ProductImage> {
        return productImages.filter { it.id == 0L }
    }
}