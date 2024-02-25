package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.UpsertProductColorInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductColorVO
import com.juloungjuloung.juju.dto.product.request.UpsertProductColorRequest

fun toUpsertVO(request: UpsertProductColorRequest): UpsertProductColorVO {
    return UpsertProductColorVO(
        productId = request.productId,
        upsertProductColorInternalVOs = request.upsertProductColorInternalRequests.map {
            UpsertProductColorInternalVO(
                id = it.id,
                color = it.color,
                additionalPrice = it.additionalPrice
            )
        }
    )
}