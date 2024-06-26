package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductTypeEnum
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class UpdateProductRequest(
    @Positive
    val id: Long,
    @NotNull
    val productType: ProductTypeEnum,
    val name: String?,
    val price: Long?,
    val weightByMilliGram: Long?,
    val isDiamond: Boolean?,
    val totalDiamondCaratX100: Int?,
    val isDisplay: Boolean?
)