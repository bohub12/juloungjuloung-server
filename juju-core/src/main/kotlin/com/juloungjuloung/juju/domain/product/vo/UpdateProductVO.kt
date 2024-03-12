package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.enums.ProductTypeEnum

data class UpdateProductVO(
    val id: Long,
    val productType: ProductTypeEnum,
    val name: String? = null,
    val price: Long? = null,
    val weightByMilliGram: Long? = null,
    val isDiamond: Boolean? = null,
    val totalDiamondCaratX100: Int? = null,
    val isDisplay: Boolean? = null
)