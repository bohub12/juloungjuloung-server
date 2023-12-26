package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductTypeEnum

data class UpdateProductRequest(
    val id: Long,
    val productType: ProductTypeEnum,
    val name: String?,
    val price: Long?,
    val weightByMilliGram: Long?,
    val isDiamond: Boolean?,
    val totalDiamondCaratX100: Int?,
    val isDisplay: Boolean?,

    val additionalBraceletRequest: UpdateBraceletAdditionalRequest?,
    val additionalNecklaceAdditionalRequest: UpdateNecklaceAdditionalRequest?
)

data class UpdateBraceletAdditionalRequest(
    val maximumLength: Int?,
    val minimumLength: Int?
)

data class UpdateNecklaceAdditionalRequest(
    val maximumLength: Int?,
    val minimumLength: Int?
)