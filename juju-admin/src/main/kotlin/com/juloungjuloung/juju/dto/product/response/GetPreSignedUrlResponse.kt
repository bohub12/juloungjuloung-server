package com.juloungjuloung.juju.dto.product.response

data class GetPreSignedUrlResponse(
    val virtualImagePath: String,
    val preSignedUrl: String
)