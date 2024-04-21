package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.enums.ProductTypeEnum.BASE
import com.juloungjuloung.juju.response.ApiResponseCode
import com.juloungjuloung.juju.utils.require
import com.juloungjuloung.juju.utils.requireNotNull
import java.time.LocalDateTime

open class Product(
    val id: Long = 0L,
    val productType: ProductTypeEnum = BASE,
    var name: String,
    val productCode: String,
    var price: Long,
    var weightByMilliGram: Long,
    var thumbnailImage: String?,
    var isDiamond: Boolean,
    var totalDiamondCaratX100: Int,
    var isDisplay: Boolean,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    init {
        requireProperties()
    }

    private fun requireProperties() {
        require(price >= 0, errorResponseCode = ApiResponseCode.PRODUCT_VALID_PRICE_MIN_ZERO)
        require(weightByMilliGram >= 0, errorResponseCode = ApiResponseCode.PRODUCT_VALID_WEIGHT_MIN_ZERO)

        if (isDisplay) {
            requireNotNull(
                thumbnailImage,
                errorResponseCode = ApiResponseCode.PRODUCT_VALID_THUMBNAIL_NOT_NULL_IF_DISPLAYED
            )
        }
    }

    open fun update(updateProductVO: UpdateProductVO) {
        updateProductVO.name?.let { this.name = updateProductVO.name }
        updateProductVO.price?.let { this.price = updateProductVO.price }
        updateProductVO.weightByMilliGram?.let { this.weightByMilliGram = updateProductVO.weightByMilliGram }
        updateProductVO.isDiamond?.let { this.isDiamond = updateProductVO.isDiamond }
        updateProductVO.totalDiamondCaratX100?.let {
            this.totalDiamondCaratX100 = updateProductVO.totalDiamondCaratX100
        }
        updateProductVO.isDisplay?.let { this.isDisplay = updateProductVO.isDisplay }

        requireProperties()
    }

    fun changeThumbnailImage(imageUrl: String) {
        this.thumbnailImage = imageUrl
    }

    fun deleteThumbnailImage() {
        this.thumbnailImage = null
    }
}

data class ProductsWithCount(
    val products: List<Product>,
    val totalElementCount: Long
)