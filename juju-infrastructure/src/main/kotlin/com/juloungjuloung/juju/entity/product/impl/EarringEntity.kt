package com.juloungjuloung.juju.entity.product.impl

import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.entity.product.ProductEntity
import com.juloungjuloung.juju.enums.TYPE_EARRING
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(TYPE_EARRING)
class EarringEntity(
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String?,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int?,
    isActive: Boolean
) : ProductEntity(
    name = name,
    productCode = productCode,
    price = price,
    weightByMilliGram = weightByMilliGram,
    thumbnailImage = thumbnailImage,
    isDiamond = isDiamond,
    totalDiamondCaratX100 = totalDiamondCaratX100,
    isActive = isActive
) {
    fun toDomain(): Earring {
        return Earring(
            id = this.id,
            name = this.name,
            productCode = this.productCode,
            price = this.price,
            weightByMilliGram = this.weightByMilliGram,
            thumbnailImage = this.thumbnailImage,
            isDiamond = this.isDiamond,
            totalDiamondCaratX100 = this.totalDiamondCaratX100,
            isActive = this.isActive,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    companion object {
        fun of(earring: Earring): EarringEntity {
            return EarringEntity(
                name = earring.name,
                productCode = earring.productCode,
                price = earring.price,
                weightByMilliGram = earring.weightByMilliGram,
                thumbnailImage = earring.thumbnailImage,
                isDiamond = earring.isDiamond,
                totalDiamondCaratX100 = earring.totalDiamondCaratX100,
                isActive = earring.isActive
            )
        }
    }
}