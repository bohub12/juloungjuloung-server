package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.GetPreSignedUrlVO
import com.juloungjuloung.juju.dto.product.response.GetPreSignedUrlResponse

fun toResponse(vo: GetPreSignedUrlVO): GetPreSignedUrlResponse {
    return GetPreSignedUrlResponse(
        virtualImagePath = vo.virtualImagePath,
        preSignedUrl = vo.preSignedUrl
    )
}