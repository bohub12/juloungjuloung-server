package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.ProductImage

data class UpsertProductImageVO(
    val productId: Long,
    val upsertProductImageInternalVOs: List<UpsertProductImageInternalVO>
) {
    fun toDomain(): List<ProductImage> {
        return upsertProductImageInternalVOs.map {
            ProductImage(
                productId = productId,
                id = it.id,
                imageUrl = it.imageUrl,
                isThumbnail = it.isThumbnail
            )
        }
    }
}

data class UpsertProductImageInternalVO(
    val id: Long = 0,
    val imageUrl: String,
    val isThumbnail: Boolean
) {
    fun isPersisted(): Boolean {
        return id != 0L
    }
}