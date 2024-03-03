package com.juloungjuloung.juju.dto.product.request

data class UpsertProductOptionRequest(
    val productId: Long,
    val optionCategory: UpsertProductOptionCategoryInternalRequest,
    val options: List<UpsertProductOptionInternalRequest>
)

data class UpsertProductOptionCategoryInternalRequest(
    val id: Long = 0L,
    val name: String
)

data class UpsertProductOptionInternalRequest(
    val id: Long = 0L,
    val name: String,
    val additionalPrice: Long = 0L
)