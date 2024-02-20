package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.SaveProductImageInternalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
import com.juloungjuloung.juju.dto.product.request.SaveProductImageInternalRequest
import com.juloungjuloung.juju.dto.product.request.SaveProductImageRequest

fun toSaveVO(request: SaveProductImageRequest): SaveProductImageVO {
    return SaveProductImageVO(
        productId = request.productId,
        saveProductImageInternalVOs = request.saveProductImageInternalRequests.map { toInternalSaveVO(it) }
    )
}

fun toInternalSaveVO(request: SaveProductImageInternalRequest): SaveProductImageInternalVO {
    return SaveProductImageInternalVO(
        imageUrl = request.imageUrl,
        isThumbnail = request.isThumbnail
    )
}