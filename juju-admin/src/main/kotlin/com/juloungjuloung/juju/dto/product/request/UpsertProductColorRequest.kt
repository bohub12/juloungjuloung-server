package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductColorEnum

data class UpsertProductColorRequest(
    val productId: Long,
    val upsertProductColorInternalRequests: List<UpsertProductColorInternalRequest>
)

data class UpsertProductColorInternalRequest(
    val id: Long = 0,
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
)