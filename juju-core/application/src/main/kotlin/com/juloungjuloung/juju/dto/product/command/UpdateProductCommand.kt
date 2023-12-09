package com.juloungjuloung.juju.dto.product.command

import com.juloungjuloung.juju.enums.ProductTypeEnum

data class UpdateProductCommand(
    val id: Long,
    val productType: ProductTypeEnum,
    val name: String?,
    val price: Long?,
    val weightByMilliGram: Long?,
    val isDiamond: Boolean?,
    val totalDiamondCaratX100: Int?,
    val isActive: Boolean?,

    val additionalBraceletRequest: UpdateBraceletAdditionalCommand?,
    val additionalNecklaceAdditionalRequest: UpdateNecklaceAdditionalCommand?
)

data class UpdateBraceletAdditionalCommand(
    val maximumLength: Int?,
    val minimumLength: Int?
)

data class UpdateNecklaceAdditionalCommand(
    val maximumLength: Int?,
    val minimumLength: Int?
)