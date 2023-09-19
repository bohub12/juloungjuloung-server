package com.juloungjuloung.juju.domain.domain.product

import com.juloungjuloung.juju.domain.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category", discriminatorType = DiscriminatorType.STRING)
open class Product(
    val name: String,
    val productCode: String,

    // mg 으로 표현. 소수점으로 발생하는 문제 차단
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String
) : BaseEntity()