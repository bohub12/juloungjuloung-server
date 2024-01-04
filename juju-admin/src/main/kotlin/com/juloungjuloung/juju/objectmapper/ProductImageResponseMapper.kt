package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.application.dto.product.GetPreSignedUrlDto
import com.juloungjuloung.juju.dto.product.response.GetPreSignedUrlResponse

class ProductImageResponseMapper {
    companion object {
        fun toResponse(dto: GetPreSignedUrlDto): GetPreSignedUrlResponse {
            return GetPreSignedUrlResponse(
                virtualImagePath = dto.virtualImagePath,
                preSignedUrl = dto.preSignedUrl
            )
        }
    }
}