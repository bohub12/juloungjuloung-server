package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.application.dto.UpdateProductDto
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

open class Product(
    val id: Long = 0L,
    val type: ProductTypeEnum,
    var name: String,
    val productCode: String,
    var price: Long,
    var weightByMilliGram: Long,
    var thumbnailImage: String?,
    var isDiamond: Boolean,
    var totalDiamondCaratX100: Int,
    var isDisplay: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    init {
        requireProperties()
    }

    private fun requireProperties() {
        require(price >= 0)
        require(weightByMilliGram >= 0)

        if (isDisplay) {
            requireNotNull(thumbnailImage)
        }
    }

    open fun update(
        updateProductDto: UpdateProductDto
    ) {
        updateProductDto.name?.let { this.name = updateProductDto.name }
        updateProductDto.price?.let { this.price = updateProductDto.price }
        updateProductDto.weightByMilliGram?.let { this.weightByMilliGram = updateProductDto.weightByMilliGram }
        updateProductDto.isDiamond?.let { this.isDiamond = updateProductDto.isDiamond }
        updateProductDto.totalDiamondCaratX100?.let {
            this.totalDiamondCaratX100 = updateProductDto.totalDiamondCaratX100
        }
        updateProductDto.isDisplay?.let { this.isDisplay = updateProductDto.isDisplay }

        requireProperties()
    }
}