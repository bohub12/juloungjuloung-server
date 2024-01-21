package com.juloungjuloung.juju.dto.product.request

data class ChangePrimaryProductImageRequest(
    val productId: Long,
    val primaryProductImageId: Long
)