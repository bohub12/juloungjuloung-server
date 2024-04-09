package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
class ProductEntity(
    val name: String,
    val productCode: String,
    val price: Long,
    // mg 으로 표현. 소수점으로 발생하는 문제 차단
    val weightByMilliGram: Long,
    val thumbnailImage: String?,
    val isDiamond: Boolean,
    // 소수점으로 발생하는 문제 차단 (carat x 100)
    val totalDiamondCaratX100: Int,
    val isDisplay: Boolean
) : BaseEntity() {
    fun toDomain(): Product {
        return Product(
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
        fun of(product: Product): ProductEntity {
            return ProductEntity(
                name = product.name,
                productCode = product.productCode,
                price = product.price,
                weightByMilliGram = product.weightByMilliGram,
                thumbnailImage = product.thumbnailImage,
                isDiamond = product.isDiamond,
                totalDiamondCaratX100 = product.totalDiamondCaratX100,
                isDisplay = product.isDisplay
            )
        }
    }
}