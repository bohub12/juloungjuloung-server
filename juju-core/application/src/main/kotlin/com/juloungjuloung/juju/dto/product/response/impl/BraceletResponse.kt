package com.juloungjuloung.juju.dto.product.response.impl

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import java.time.LocalDateTime

class BraceletResponse(
    id: Long,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String?,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int,
    isActive: Boolean,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime,

    val maximumLength: Int,
    val minimumLength: Int
) : ProductResponse(
    id = id,
    type = ProductTypeEnum.BRACELET.name,
    name = name,
    productCode = productCode,
    price = price,
    weightByMilliGram = weightByMilliGram,
    thumbnailImage = thumbnailImage,
    isDiamond = isDiamond,
    totalDiamondCaratX100 = totalDiamondCaratX100,
    isActive = isActive,
    createdAt = createdAt,
    updatedAt = updatedAt
) {
    companion object {
        fun of(bracelet: Bracelet): BraceletResponse {
            return BraceletResponse(
                id = bracelet.id!!,
                name = bracelet.name,
                productCode = bracelet.productCode,
                price = bracelet.price,
                weightByMilliGram = bracelet.weightByMilliGram,
                thumbnailImage = bracelet.thumbnailImage,
                isDiamond = bracelet.isDiamond,
                totalDiamondCaratX100 = bracelet.totalDiamondCaratX100 ?: 0,
                isActive = bracelet.isActive,
                maximumLength = bracelet.maximumLength,
                minimumLength = bracelet.minimumLength,
                createdAt = bracelet.createdAt!!,
                updatedAt = bracelet.updatedAt!!
            )
        }
    }
}