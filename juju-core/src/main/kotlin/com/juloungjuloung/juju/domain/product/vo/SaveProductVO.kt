package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.enums.ProductTypeEnum

data class SaveProductVO(
    val productType: ProductTypeEnum,
    val name: String,
    val price: Long,
    val weightByMilliGram: Long,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,
    val isDisplay: Boolean
)