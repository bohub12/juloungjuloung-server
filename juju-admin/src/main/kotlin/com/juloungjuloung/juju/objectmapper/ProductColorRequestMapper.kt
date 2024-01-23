package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.SaveProductColorInternalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductColorVO
import com.juloungjuloung.juju.dto.product.request.SaveProductColorRequest

fun toSaveVO(request: SaveProductColorRequest): SaveProductColorVO {
    return SaveProductColorVO(
        productId = request.productId,
        saveProductColorInternalVOs = request.saveProductImageInternalRequests.map {
            SaveProductColorInternalVO(
                color = it.color,
                additionalPrice = it.additionalPrice
            )
        }
    )
}