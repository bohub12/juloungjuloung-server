package com.juloungjuloung.juju.domain.domain.product

import com.juloungjuloung.juju.domain.domain.BaseEntity
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.STRING)
class Product(
    val name: String,
    val productCode: String,

    // mg 으로 표현. 소수점으로 발생하는 문제 차단
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
    val isActive: Boolean
) : BaseEntity()