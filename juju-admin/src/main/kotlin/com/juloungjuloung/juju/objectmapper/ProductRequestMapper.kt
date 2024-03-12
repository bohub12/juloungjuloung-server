package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest
import com.juloungjuloung.juju.dto.product.request.UpdateProductRequest

fun toSaveVO(request: SaveProductRequest): SaveProductVO {
    return SaveProductVO(
        productType = request.productType,
        name = request.name,
        price = request.price,
        weightByMilliGram = request.weightByMilliGram,
        isDiamond = request.isDiamond,
        totalDiamondCaratX100 = request.totalDiamondCaratX100,
        isDisplay = request.isDisplay
    )
}

fun toUpdateVO(request: UpdateProductRequest): UpdateProductVO {
    return UpdateProductVO(
        id = request.id,
        productType = request.productType,
        name = request.name,
        price = request.price,
        weightByMilliGram = request.weightByMilliGram,
        isDiamond = request.isDiamond,
        totalDiamondCaratX100 = request.totalDiamondCaratX100,
        isDisplay = request.isDisplay
    )
}