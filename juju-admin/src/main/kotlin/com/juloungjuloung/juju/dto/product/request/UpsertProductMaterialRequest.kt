package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductMaterialEnum

data class UpsertProductMaterialRequest(
    val productId: Long,
    val upsertProductMaterialInternalRequest: List<UpsertProductMaterialInternalRequest>
)

data class UpsertProductMaterialInternalRequest(
    val id: Long = 0L,
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
)