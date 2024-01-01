package com.juloungjuloung.juju.dto.product.request

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.enums.ProductTypeEnum

data class SaveProductRequest(
    val productType: ProductTypeEnum,
    val name: String,
    val price: Long,
    val weightByMilliGram: Long,
    val isDiamond: Boolean,
    val totalDiamondCaratX100: Int,

    val additionalBraceletRequest: SaveBraceletAdditionalRequest?,
    val additionalNecklaceRequest: SaveNecklaceAdditionalRequest?
) {
    init {
        if (ProductTypeEnum.BRACELET == productType) {
            requireNotNull(additionalBraceletRequest)
        }

        if (ProductTypeEnum.NECKLACE == productType) {
            requireNotNull(additionalNecklaceRequest)
        }
    }

    fun toBracelet(): Bracelet {
        return Bracelet(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = null,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = false,
            maximumLength = additionalBraceletRequest!!.maximumLength,
            minimumLength = additionalBraceletRequest.minimumLength
        )
    }

    fun toEarring(): Earring {
        return Earring(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = null,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = false
        )
    }

    fun toNecklace(): Necklace {
        return Necklace(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = null,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = false,
            maximumLength = additionalNecklaceRequest!!.maximumLength,
            minimumLength = additionalNecklaceRequest.minimumLength
        )
    }

    fun toRing(): Ring {
        return Ring(
            name = name,
            productCode = "dd", // TODO : productCode
            price = price,
            weightByMilliGram = weightByMilliGram,
            thumbnailImage = null,
            isDiamond = isDiamond,
            totalDiamondCaratX100 = totalDiamondCaratX100,
            isDisplay = false
        )
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