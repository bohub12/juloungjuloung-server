package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.ProductOption
import com.juloungjuloung.juju.domain.product.ProductOptionCategory
import com.juloungjuloung.juju.domain.product.ProductOptionInfo
import com.juloungjuloung.juju.domain.product.ProductOptions

data class UpsertProductOptionVO(
    val productId: Long,
    val optionCategory: UpsertProductOptionCategoryInternalVO,
    val options: List<UpsertProductOptionInternalVO>
) {
    fun toDomainInfo(): ProductOptionInfo {
        return ProductOptionInfo(
            optionCategory = optionCategory.toDomain(productId),
            options = ProductOptions(options.map { it.toDomain(optionCategory.id) })
        )
    }
}

data class UpsertProductOptionCategoryInternalVO(
    val id: Long = 0L,
    val name: String
) {
    fun toDomain(productId: Long): ProductOptionCategory {
        return ProductOptionCategory(
            id = id,
            productId = productId,
            name = name
        )
    }
}

data class UpsertProductOptionInternalVO(
    val id: Long = 0L,
    val name: String,
    val additionalPrice: Long = 0
) {
    fun toDomain(productOptionCategoryId: Long): ProductOption {
        return ProductOption(
            id = id,
            productOptionCategoryId = productOptionCategoryId,
            name = name,
            additionalPrice = additionalPrice
        )
    }
}