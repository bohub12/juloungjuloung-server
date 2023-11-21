package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.Product
import java.time.LocalDateTime

class Ring(
    id: Long? = null,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String? = null,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int?,
    isActive: Boolean = false,
    createdAt: LocalDateTime? = null,
    updatedAt: LocalDateTime? = null
) : Product(
    id = id,
    type = ProductTypeEnum.NECKLACE,
    name = name,
    productCode = productCode,
    price = price,
    weightByMilliGram = weightByMilliGram,
    thumbnailImage = thumbnailImage,
    isDiamond = isDiamond,
    totalDiamondCaratX100 = totalDiamondCaratX100,
    isActive = isActive,
    createdAt = createdAt,
    updatedAt = updatedAt
)