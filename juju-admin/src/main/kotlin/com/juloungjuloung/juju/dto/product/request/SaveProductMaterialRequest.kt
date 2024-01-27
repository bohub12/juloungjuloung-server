package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductMaterialEnum

data class SaveProductMaterialRequest(
    val productId: Long,
    val saveProductMaterialInternalRequest: List<SaveProductMaterialInternalRequest>
)

data class SaveProductMaterialInternalRequest(
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
)