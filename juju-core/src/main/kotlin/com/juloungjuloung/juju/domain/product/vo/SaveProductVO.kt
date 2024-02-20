package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.enums.ProductTypeEnum.BRACELET
import com.juloungjuloung.juju.enums.ProductTypeEnum.EARRING
import com.juloungjuloung.juju.enums.ProductTypeEnum.NECKLACE
import com.juloungjuloung.juju.enums.ProductTypeEnum.RING
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ENUM

data class SaveProductVO(
    val productType: ProductTypeEnum,
    val name: String,
    val price: Long,
    val weightByMilliGram: Long,
    val thumbnailImage: String,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,
    val isDisplay: Boolean,

    val saveBraceletAdditionalVO: SaveBraceletAdditionalVO?,
    val saveNecklaceAdditionalVO: SaveNecklaceAdditionalVO?
) {
    fun toDomain(): Product {
        return when (productType) {
            BRACELET -> toBracelet()
            EARRING -> toEarring()
            NECKLACE -> toNecklace()
            RING -> toRing()
            else -> throw BusinessLogicException(BAD_REQUEST_ENUM)
        }
    }

    fun hasThumbnailImage(): Boolean {
        return thumbnailImage.isNotBlank()
    }

    private fun toBracelet(): Bracelet {
        return Bracelet(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = thumbnailImage,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = isDisplay,
            maximumLength = saveBraceletAdditionalVO!!.maximumLength,
            minimumLength = saveBraceletAdditionalVO.minimumLength
        )
    }

    private fun toEarring(): Earring {
        return Earring(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = thumbnailImage,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = isDisplay
        )
    }

    private fun toNecklace(): Necklace {
        return Necklace(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = thumbnailImage,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = isDisplay,
            maximumLength = saveNecklaceAdditionalVO!!.maximumLength,
            minimumLength = saveNecklaceAdditionalVO.minimumLength
        )
    }

    private fun toRing(): Ring {
        return Ring(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = thumbnailImage,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = isDisplay
        )
    }
}

data class SaveBraceletAdditionalVO(
    val maximumLength: Int,
    val minimumLength: Int
)

data class SaveNecklaceAdditionalVO(
    val maximumLength: Int,
    val minimumLength: Int
)