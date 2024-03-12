package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

class Earring(
    id: Long = 0L,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String? = null,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int,
    isDisplay: Boolean,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now()
) : Product(
    id = id,
    productType = ProductTypeEnum.EARRING,
    name = name,
    productCode = productCode,
    price = price,
    weightByMilliGram = weightByMilliGram,
    thumbnailImage = thumbnailImage,
    isDiamond = isDiamond,
    totalDiamondCaratX100 = totalDiamondCaratX100,
    isDisplay = isDisplay,
    createdAt = createdAt,
    updatedAt = updatedAt
) {
    companion object {
        fun create(saveProductVO: SaveProductVO): Earring {
            return Earring(
                name = saveProductVO.name,
                productCode = "dd", // TODO : productCode
                price = saveProductVO.price,
                weightByMilliGram = saveProductVO.weightByMilliGram,
                isDiamond = saveProductVO.isDiamond,
                totalDiamondCaratX100 = saveProductVO.totalDiamondCaratX100,
                isDisplay = saveProductVO.isDisplay
            )
        }
    }
}