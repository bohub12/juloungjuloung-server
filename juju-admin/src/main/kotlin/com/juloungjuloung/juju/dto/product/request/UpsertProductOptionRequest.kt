package com.juloungjuloung.juju.dto.product.request

data class UpsertProductOptionRequest(
    val optionCategory: UpsertProductOptionCategoryRequest,
    val options: List<UpsertProductOptionInternalRequest>
)

data class UpsertProductOptionCategoryRequest(
    val id: Long = 0L,
    val name: String
)

data class UpsertProductOptionInternalRequest(
    val id: Long = 0L,
    val name: String,
    val additionalPrice: Int = 0
)