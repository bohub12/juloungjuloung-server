package com.juloungjuloung.juju.entity.product.impl

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.entity.product.ProductEntity
import com.juloungjuloung.juju.enums.TYPE_BRACELET
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(TYPE_BRACELET)
class BraceletEntity(
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String? = null,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int,
    isDisplay: Boolean
) : ProductEntity(
    name = name,
    productCode = productCode,
    price = price,
    weightByMilliGram = weightByMilliGram,
    thumbnailImage = thumbnailImage,
    isDiamond = isDiamond,
    totalDiamondCaratX100 = totalDiamondCaratX100,
    isDisplay = isDisplay
) {
    override fun toDomain(): Bracelet {
        return Bracelet(
            id = this.id,
            name = this.name,
            productCode = this.productCode,
            price = this.price,
            weightByMilliGram = this.weightByMilliGram,
            thumbnailImage = this.thumbnailImage,
            isDiamond = this.isDiamond,
            totalDiamondCaratX100 = this.totalDiamondCaratX100,
            isDisplay = this.isDisplay,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    companion object {
        fun of(bracelet: Bracelet): BraceletEntity {
            return BraceletEntity(
                name = bracelet.name,
                productCode = bracelet.productCode,
                price = bracelet.price,
                weightByMilliGram = bracelet.weightByMilliGram,
                thumbnailImage = bracelet.thumbnailImage,
                isDiamond = bracelet.isDiamond,
                totalDiamondCaratX100 = bracelet.totalDiamondCaratX100,
                isDisplay = bracelet.isDisplay
            )
        }
    }
}