package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.UpsertProductOptionCategoryInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductOptionInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductOptionVO
import com.juloungjuloung.juju.dto.product.request.UpsertProductOptionCategoryInternalRequest
import com.juloungjuloung.juju.dto.product.request.UpsertProductOptionInternalRequest
import com.juloungjuloung.juju.dto.product.request.UpsertProductOptionRequest

fun toUpsertVO(request: UpsertProductOptionRequest): UpsertProductOptionVO {
    return UpsertProductOptionVO(
        productId = request.productId,
        optionCategory = toUpsertOptionCategoryInternalVO(request.optionCategory),
        options = request.options.map { toUpsertOptionInternalVO(it) }
    )
}

private fun toUpsertOptionCategoryInternalVO(request: UpsertProductOptionCategoryInternalRequest):
    UpsertProductOptionCategoryInternalVO {
    return UpsertProductOptionCategoryInternalVO(
        id = request.id,
        name = request.name
    )
}

private fun toUpsertOptionInternalVO(request: UpsertProductOptionInternalRequest): UpsertProductOptionInternalVO {
    return UpsertProductOptionInternalVO(
        id = request.id,
        name = request.name,
        additionalPrice = request.additionalPrice
    )
}