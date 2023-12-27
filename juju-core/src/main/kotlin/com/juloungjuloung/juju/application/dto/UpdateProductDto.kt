package com.juloungjuloung.juju.application.dto

import com.juloungjuloung.juju.enums.ProductTypeEnum

data class UpdateProductDto(
    val id: Long,
    val productType: ProductTypeEnum,
    val name: String? = null,
    val price: Long? = null,
    val weightByMilliGram: Long? = null,
    val isDiamond: Boolean? = null,
    val totalDiamondCaratX100: Int? = null,
    val isDisplay: Boolean? = null,

    val additionalBraceletRequest: UpdateBraceletAdditionalDto? = null,
    val additionalNecklaceAdditionalRequest: UpdateNecklaceAdditionalDto? = null
)

data class UpdateBraceletAdditionalDto(
    val maximumLength: Int,
    val minimumLength: Int
)

data class UpdateNecklaceAdditionalDto(
    val maximumLength: Int,
    val minimumLength: Int
)