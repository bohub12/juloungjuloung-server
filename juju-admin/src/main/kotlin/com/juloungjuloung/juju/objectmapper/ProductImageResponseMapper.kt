package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.vo.GetPreSignedUrlVO
import com.juloungjuloung.juju.dto.product.response.GetPreSignedUrlResponse
import com.juloungjuloung.juju.dto.product.response.ProductImageResponse

fun toResponse(vo: GetPreSignedUrlVO): GetPreSignedUrlResponse {
    return GetPreSignedUrlResponse(
        virtualImagePath = vo.virtualImagePath,
        preSignedUrl = vo.preSignedUrl
    )
}

fun toResponse(productImage: ProductImage): ProductImageResponse {
    return ProductImageResponse(
        id = productImage.id,
        productId = productImage.productId,
        imageUrl = productImage.imageUrl,
        isThumbnail = productImage.isThumbnail
    )
}