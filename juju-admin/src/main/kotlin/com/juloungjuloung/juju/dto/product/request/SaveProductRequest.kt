package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductTypeEnum

data class SaveProductRequest(

    val productType: ProductTypeEnum,
    val name: String,
    val price: Long,
    val weightByMilliGram: Long,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int?,

    val additionalBraceletRequest: SaveBraceletAdditionalRequest?,
    val additionalNecklaceRequest: SaveNecklaceAdditionalRequest?
)

data class SaveBraceletAdditionalRequest(
    val maximumLength: Int,
    val minimumLength: Int
)

data class SaveNecklaceAdditionalRequest(
    val maximumLength: Int,
    val minimumLength: Int
)