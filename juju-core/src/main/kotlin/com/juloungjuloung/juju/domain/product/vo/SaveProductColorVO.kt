package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.enums.ProductColorEnum

data class SaveProductColorVO(
    val productId: Long,
    val saveProductImageInternalRequests: List<SaveProductColorInternalVO>
) {
    fun toDomain(): List<ProductColor> {
        return saveProductImageInternalRequests.map {
            ProductColor(productId = productId, color = it.color, additionalPrice = it.additionalPrice)
        }
    }
}

data class SaveProductColorInternalVO(
    val color: ProductColorEnum,
    val additionalPrice: Int = 0
)