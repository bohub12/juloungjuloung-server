package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import java.time.LocalDateTime

open class Product(
    val id: Long = 0L,
    val productType: ProductTypeEnum,
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
        updateProductVO: UpdateProductVO
    ) {
        updateProductVO.name?.let { this.name = updateProductVO.name }
        updateProductVO.price?.let { this.price = updateProductVO.price }
        updateProductVO.weightByMilliGram?.let { this.weightByMilliGram = updateProductVO.weightByMilliGram }
        updateProductVO.thumbnailImage?.let { this.thumbnailImage = updateProductVO.thumbnailImage }
        updateProductVO.isDiamond?.let { this.isDiamond = updateProductVO.isDiamond }
        updateProductVO.totalDiamondCaratX100?.let {
            this.totalDiamondCaratX100 = updateProductVO.totalDiamondCaratX100
        }
        updateProductVO.isDisplay?.let { this.isDisplay = updateProductVO.isDisplay }

        requireProperties()
    }
}