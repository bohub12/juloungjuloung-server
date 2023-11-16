package com.juloungjuloung.juju.entity.product

import com.juloungjuloung.juju.entity.BaseEntity
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.STRING)
abstract class ProductEntity(
    val name: String,
    val productCode: String,
    val price: Long,
    // mg 으로 표현. 소수점으로 발생하는 문제 차단
    val weightByMilliGram: Long,
    val thumbnailImage: String,
    val isDiamond: Boolean,
    // 소수점으로 발생하는 문제 차단 (carat x 100)
    val totalDiamondCaratX100: Int?,
    val isActive: Boolean
) : BaseEntity()