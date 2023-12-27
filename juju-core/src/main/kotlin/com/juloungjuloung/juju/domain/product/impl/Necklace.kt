package com.juloungjuloung.juju.domain.product.impl

import com.juloungjuloung.juju.application.dto.UpdateProductDto
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

class Necklace(
    id: Long = 0L,
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String?,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int,
    isDisplay: Boolean,
    createdAt: LocalDateTime = LocalDateTime.now(),
    updatedAt: LocalDateTime = LocalDateTime.now(),

    var maximumLength: Int,
    var minimumLength: Int
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
    isDisplay = isDisplay,
    createdAt = createdAt,
    updatedAt = updatedAt
) {
    override fun update(updateProductDto: UpdateProductDto) {
        super.update(updateProductDto)

        updateProductDto.additionalNecklaceAdditionalRequest?.let {
            this.maximumLength = updateProductDto.additionalNecklaceAdditionalRequest.maximumLength
            this.minimumLength = updateProductDto.additionalNecklaceAdditionalRequest.minimumLength
        }
    }
}