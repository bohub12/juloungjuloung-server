package com.juloungjuloung.juju.domain.product

import java.time.LocalDateTime

data class ProductOptionCategory(
    val id: Long = 0L,
    val productId: Long,
    val name: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun isPersisted(): Boolean {
        return id != 0L
    }
}

data class ProductOptionInfo(
    val optionCategory: ProductOptionCategory,
    val options: ProductOptions
) {
    companion object {
        fun from(productOptionCategory: ProductOptionCategory, productOptions: List<ProductOption>): ProductOptionInfo {
            return ProductOptionInfo(
                optionCategory = productOptionCategory,
                options = ProductOptions(productOptions.getByProductOptionCategoryId(productOptionCategory.id))
            )
        }
    }

    fun updateProductOptionCategoryId(productOptionCategoryId: Long) {
        options.updateProductOptionCategoryId(productOptionCategoryId)
    }

    fun isPersistedProductOptionCategory(): Boolean {
        return optionCategory.id != 0L
    }

    fun filterPersistedOption(): List<ProductOption> {
        return options.options.filter { it.id != 0L }
    }
}