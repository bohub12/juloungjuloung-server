package com.juloungjuloung.juju.dto.product.response

import com.juloungjuloung.juju.common.constant.TYPE_BASE
import com.juloungjuloung.juju.domain.product.Product
import java.time.LocalDateTime

open class ProductResponse(
    val id: Long,
    val type: String,
    val name: String,
    val productCode: String,
    val price: Long,
    val weightByMilliGram: Long,
    val thumbnailImage: String,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!,
                type = TYPE_BASE,
                name = product.name,
                productCode = product.productCode,
                price = product.price,
                weightByMilliGram = product.weightByMilliGram,
                thumbnailImage = product.thumbnailImage,
                isDiamond = product.isDiamond,
                totalDiamondCaratX100 = product.totalDiamondCaratX100 ?: 0,
                isActive = product.isActive,
                createdAt = product.createdAt!!,
                updatedAt = product.updatedAt!!
            )
        }
    }
}