package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.UpsertProductImageInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductImageVO
import com.juloungjuloung.juju.dto.product.request.UpsertProductImageInternalRequest
import com.juloungjuloung.juju.dto.product.request.UpsertProductImageRequest

fun toUpsertVO(request: UpsertProductImageRequest): UpsertProductImageVO {
    return UpsertProductImageVO(
        productId = request.productId,
        upsertProductImageInternalVOs = request.upsertProductImageInternalRequests.map { toUpsertInternalVO(it) }
    )
}

fun toUpsertInternalVO(request: UpsertProductImageInternalRequest): UpsertProductImageInternalVO {
    return UpsertProductImageInternalVO(
        id = request.id,
        imageUrl = request.imageUrl,
        isThumbnail = request.isThumbnail
    )
}