package com.juloungjuloung.juju.dto.product.response

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductOptionInfo
import com.juloungjuloung.juju.objectmapper.toResponse

data class ProductDetailResponse(
    val product: ProductResponse,
    val productOptionInfos: List<ProductOptionInfoResponse>
) {
    companion object {
        fun from(
            product: Product,
            productOptionInfos: List<ProductOptionInfo>
        ): ProductDetailResponse {
            return ProductDetailResponse(
                product = toResponse(product),
                productOptionInfos = productOptionInfos.map { toResponse(it) }
            )
        }
    }
}