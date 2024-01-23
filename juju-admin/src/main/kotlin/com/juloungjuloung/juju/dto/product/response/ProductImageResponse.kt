package com.juloungjuloung.juju.dto.product.response

data class ProductImageResponse(
    val id: Long = 0L,
    val productId: Long,
    val imageUrl: String,
    var isPrimary: Boolean
)