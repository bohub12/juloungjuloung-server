package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.SaveProductMaterialInternalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductMaterialVO
import com.juloungjuloung.juju.dto.product.request.SaveProductMaterialRequest

fun toSaveVO(request: SaveProductMaterialRequest): SaveProductMaterialVO {
    return SaveProductMaterialVO(
        productId = request.productId,
        saveProductMaterialInternalVOs = request.saveProductMaterialInternalRequest.map {
            SaveProductMaterialInternalVO(
                material = it.material,
                additionalPrice = it.additionalPrice
            )
        }
    )
}