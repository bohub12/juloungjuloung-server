package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ENUM
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

    val isDisplay: Boolean,

    val saveBraceletAdditionalRequest: SaveBraceletAdditionalRequest?,
    val saveNecklaceAdditionalRequest: SaveNecklaceAdditionalRequest?
) {
    init {
        if (ProductTypeEnum.BASE == productType) {
            throw BusinessLogicException(BAD_REQUEST_ENUM)
        }

        if (ProductTypeEnum.BRACELET == productType) {
            requireNotNull(saveBraceletAdditionalRequest)
        }

        if (ProductTypeEnum.NECKLACE == productType) {
            requireNotNull(saveNecklaceAdditionalRequest)
        }
    }
}

data class SaveBraceletAdditionalRequest(
    val maximumLength: Int,
    val minimumLength: Int
)

data class SaveNecklaceAdditionalRequest(
    val maximumLength: Int,
    val minimumLength: Int
)