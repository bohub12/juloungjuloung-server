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
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,
    val isDisplay: Boolean
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

    private fun toBracelet(): Bracelet {
        return Bracelet(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = isDisplay
        )
    }

    private fun toEarring(): Earring {
        return Earring(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
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
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = isDisplay
        )
    }

    private fun toRing(): Ring {
        return Ring(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = isDisplay
        )
    }
}