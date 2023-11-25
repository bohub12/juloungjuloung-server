package com.juloungjuloung.juju.dto.product.response

import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import java.time.LocalDateTime

open class ProductResponse(
    val id: Long,
    val type: String,
    val name: String,
    val productCode: String,
    val price: Long,
    val weightByMilliGram: Long,
    val thumbnailImage: String?,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun of(productCommandResult: ProductCommandResult): ProductResponse {
            return ProductResponse(
                id = productCommandResult.id,
                type = productCommandResult.type,
                name = productCommandResult.name,
                productCode = productCommandResult.productCode,
                price = productCommandResult.price,
                weightByMilliGram = productCommandResult.weightByMilliGram,
                thumbnailImage = productCommandResult.thumbnailImage,
                isDiamond = productCommandResult.isDiamond,
                totalDiamondCaratX100 = productCommandResult.totalDiamondCaratX100,
                isActive = productCommandResult.isActive,
                createdAt = productCommandResult.createdAt,
                updatedAt = productCommandResult.updatedAt
            )
        }
    }
}