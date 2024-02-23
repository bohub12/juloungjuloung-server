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

fun List<ProductImage>.validate(): ProductImages {
    return ProductImages(this)
}

fun List<ProductImage>.filterPersisted(): List<ProductImage> {
    return this.filter { it.id != 0L }
}

fun List<ProductImage>.filterNotPersisted(): List<ProductImage> {
    return this.filter { it.id == 0L }
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

        if (productImages.isNotEmpty() && productImages.filter { it.isThumbnail }.size != 1) {
            throw BusinessLogicException(PRODUCT_IMAGE_THUMBNAIL_NOT_ONE)
        }
    }

    fun containsThumbnail(): Boolean {
        return productImages.any { it.isThumbnail }
    }

    fun getThumbnail(): ProductImage {
        return productImages.first { it.isThumbnail }
    }

    fun getProductImageIds(): List<Long> {
        return productImages.map { it.id }
    }
}