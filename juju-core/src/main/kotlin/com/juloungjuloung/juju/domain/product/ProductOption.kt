package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION
import com.juloungjuloung.juju.utils.require
import java.time.LocalDateTime

data class ProductOption(
    val id: Long = 0L,
    var productOptionCategoryId: Long,
    val name: String,
    var additionalPrice: Long = 0L,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

fun List<ProductOption>.getByProductOptionCategoryId(productOptionCategoryId: Long): List<ProductOption> {
    return this.filter { it.productOptionCategoryId == productOptionCategoryId }
}

data class ProductOptions(
    val options: List<ProductOption>
) {
    init {
        requireProperties()
    }

    private fun requireProperties() {
        require(options.isNotEmpty(), PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION)
    }

    fun updateProductOptionCategoryId(productOptionCategoryId: Long) {
        options.map { it.productOptionCategoryId = productOptionCategoryId }
    }

    fun filterPersisted(): List<ProductOption> {
        return options.filter { it.id != 0L }
    }

    fun filterNotPersisted(): List<ProductOption> {
        return options.filter { it.id == 0L }
    }
}