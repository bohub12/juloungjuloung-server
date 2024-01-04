package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.enums.ProductTypeEnum

data class UpdateProductVO(
    val id: Long,
    val productType: ProductTypeEnum,
    val name: String? = null,
    val price: Long? = null,
    val weightByMilliGram: Long? = null,
    val thumbnailImage: String?,
    val isDiamond: Boolean? = null,
    val totalDiamondCaratX100: Int? = null,
    val isDisplay: Boolean? = null,

    val additionalBraceletVO: UpdateBraceletAdditionalVO? = null,
    val additionalNecklaceVO: UpdateNecklaceAdditionalVO? = null
)

data class UpdateBraceletAdditionalVO(
    val maximumLength: Int,
    val minimumLength: Int
)

data class UpdateNecklaceAdditionalVO(
    val maximumLength: Int,
    val minimumLength: Int
)