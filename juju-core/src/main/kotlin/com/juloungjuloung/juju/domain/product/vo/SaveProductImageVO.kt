package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.ProductImages

data class SaveProductImageVO(
    val productId: Long,
    val saveProductImageInternalVOs: List<SaveProductImageInternalVO>
) {
    fun toDomain(product: Product): ProductImages {
        return ProductImages(
            productImages = saveProductImageInternalVOs.map {
                ProductImage(
                    product = product,
                    imageUrl = it.imageUrl,
                    isPrimary = it.isPrimary
                )
            }
        )
    }
}

data class SaveProductImageInternalVO(
    val imageUrl: String,
    val isPrimary: Boolean
)