package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_PRIMARY_NOT_ONE
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_SIZE_EXCEED_MAX
import java.time.LocalDateTime

data class ProductImage(
    val id: Long = 0L,
    val product: Product,
    val imageUrl: String,
    val isPrimary: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

data class ProductImages(
    val productImages: List<ProductImage>
) {
    init {
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
}