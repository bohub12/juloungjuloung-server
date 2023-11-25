package com.juloungjuloung.juju.entity.product.impl

import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.entity.product.ProductEntity
import com.juloungjuloung.juju.enums.TYPE_NECKLACE
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(TYPE_NECKLACE)
class NecklaceEntity(
    name: String,
    productCode: String,
    price: Long,
    weightByMilliGram: Long,
    thumbnailImage: String?,
    isDiamond: Boolean,
    totalDiamondCaratX100: Int?,
    isActive: Boolean,
    @Column(name = "necklace_maximum_length")
    val maximumLength: Int,

    @Column(name = "necklace_minimum_length")
    val minimumLength: Int
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
    fun toDomain(): Necklace {
        return Necklace(
            id = this.id,
            name = this.name,
            productCode = this.productCode,
            price = this.price,
            weightByMilliGram = this.weightByMilliGram,
            thumbnailImage = this.thumbnailImage,
            isDiamond = this.isDiamond,
            totalDiamondCaratX100 = this.totalDiamondCaratX100,
            isActive = this.isActive,
            maximumLength = this.maximumLength,
            minimumLength = this.minimumLength,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    companion object {
        fun of(necklace: Necklace): NecklaceEntity {
            return NecklaceEntity(
                name = necklace.name,
                productCode = necklace.productCode,
                price = necklace.price,
                weightByMilliGram = necklace.weightByMilliGram,
                thumbnailImage = necklace.thumbnailImage,
                isDiamond = necklace.isDiamond,
                totalDiamondCaratX100 = necklace.totalDiamondCaratX100,
                isActive = necklace.isActive,
                maximumLength = necklace.maximumLength,
                minimumLength = necklace.minimumLength
            )
        }
    }
}