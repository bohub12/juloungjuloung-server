package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.ProductImage

data class SaveProductImageVO(
    val productId: Long,
    val saveProductImageInternalVOs: List<SaveProductImageInternalVO>
) {
    companion object {
        fun buildForThumbnail(productId: Long, imageUrl: String): SaveProductImageVO {
            return SaveProductImageVO(
                productId = productId,
                saveProductImageInternalVOs = listOf(
                    SaveProductImageInternalVO(
                        imageUrl = imageUrl,
                        isThumbnail = true
                    )
                )
            )
        }
    }

    fun toDomain(): List<ProductImage> {
        return saveProductImageInternalVOs.map {
            ProductImage(
                productId = productId,
                imageUrl = it.imageUrl,
                isThumbnail = it.isThumbnail
            )
        }
    }
}

data class SaveProductImageInternalVO(
    val imageUrl: String,
    val isThumbnail: Boolean
)