package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.product.ProductMaterial

data class Product(
    val name: String,
    val productCode: String,
    val weightByMilliGram: Int,
    val material: ProductMaterial,
    val thumbnailImage: String,
    val isActive: Boolean
)