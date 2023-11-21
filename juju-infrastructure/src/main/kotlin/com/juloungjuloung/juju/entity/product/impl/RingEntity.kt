package com.juloungjuloung.juju.entity.product.impl

import com.juloungjuloung.juju.common.constant.TYPE_RING
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.entity.product.ProductEntity
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(TYPE_RING)
class RingEntity(
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
    fun toDomain(): Ring {
        return Ring(
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
        fun of(ring: Ring): RingEntity {
            return RingEntity(
                name = ring.name,
                productCode = ring.productCode,
                price = ring.price,
                weightByMilliGram = ring.weightByMilliGram,
                thumbnailImage = ring.thumbnailImage,
                isDiamond = ring.isDiamond,
                totalDiamondCaratX100 = ring.totalDiamondCaratX100,
                isActive = ring.isActive
            )
        }
    }
}