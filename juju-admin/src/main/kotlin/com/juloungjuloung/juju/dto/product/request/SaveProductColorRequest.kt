package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductColorEnum

data class SaveProductColorRequest(
    val productId: Long,
    val saveProductColorInternalRequests: List<SaveProductColorInternalRequest>
)

data class SaveProductColorInternalRequest(
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
)