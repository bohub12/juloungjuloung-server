package com.juloungjuloung.juju.dto.product.request

data class UpsertProductImageRequest(
    val productId: Long,
    val upsertProductImageInternalRequests: List<UpsertProductImageInternalRequest>
)

data class UpsertProductImageInternalRequest(
    val id: Long = 0,
    val imageUrl: String,
    val isThumbnail: Boolean
)