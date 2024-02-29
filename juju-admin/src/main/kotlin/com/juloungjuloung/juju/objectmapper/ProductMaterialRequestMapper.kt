package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.UpsertProductMaterialInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductMaterialVO
import com.juloungjuloung.juju.dto.product.request.UpsertProductMaterialRequest

fun toUpsertVO(request: UpsertProductMaterialRequest): UpsertProductMaterialVO {
    return UpsertProductMaterialVO(
        productId = request.productId,
        upsertProductMaterialInternalVOs = request.upsertProductMaterialInternalRequest.map {
            UpsertProductMaterialInternalVO(
                id = it.id,
                material = it.material,
                additionalPrice = it.additionalPrice
            )
        }
    )
}