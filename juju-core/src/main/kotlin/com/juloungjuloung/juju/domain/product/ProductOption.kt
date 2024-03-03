package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION
import java.time.LocalDateTime

data class ProductOption(
    val id: Long = 0L,
    var productOptionCategoryId: Long,
    val name: String,
    var additionalPrice: Long = 0L,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

data class ProductOptions(
    val options: List<ProductOption>
) {
    init {
        requireProperties()
    }

    private fun requireProperties() {
        if (options.isEmpty()) {
            throw BusinessLogicException(PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION)
        }
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