package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_VALID_BAD_PRODUCT_TYPE_ENUM_IN_SAVE_CONDITION
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero

data class SaveProductRequest(
    @NotNull
    val productType: ProductTypeEnum,
    @NotBlank
    val name: String,
    @PositiveOrZero
    val price: Long,
    @PositiveOrZero
    val weightByMilliGram: Long,
    val isDiamond: Boolean,
    @PositiveOrZero
    val totalDiamondCaratX100: Int,
    val isDisplay: Boolean
) {
    init {
        if (ProductTypeEnum.BASE == productType) {
            throw BusinessLogicException(PRODUCT_VALID_BAD_PRODUCT_TYPE_ENUM_IN_SAVE_CONDITION)
        }
    }
}