package com.juloungjuloung.juju.dto.product.request

data class SaveProductImageRequest(
    val productId: Long,
    val saveProductImageInternalRequests: List<SaveProductImageInternalRequest>
)

data class SaveProductImageInternalRequest(
    val imageUrl: String,
    val isThumbnail: Boolean
)