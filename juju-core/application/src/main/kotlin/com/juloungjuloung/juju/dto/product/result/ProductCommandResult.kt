package com.juloungjuloung.juju.dto.product.result

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.enums.TYPE_BASE
import java.time.LocalDateTime

open class ProductCommandResult(
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
        fun of(product: Product): ProductCommandResult {
            return ProductCommandResult(
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