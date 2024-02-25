package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.enums.ProductColorEnum

data class UpsertProductColorVO(
    val productId: Long,
    val upsertProductColorInternalVOs: List<UpsertProductColorInternalVO>
) {
    fun toDomain(): List<ProductColor> {
        return upsertProductColorInternalVOs.map {
            ProductColor(
                id = it.id,
                productId = productId,
                color = it.color,
                additionalPrice = it.additionalPrice
            )
        }
    }
}

data class UpsertProductColorInternalVO(
    val id: Long = 0,
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
)