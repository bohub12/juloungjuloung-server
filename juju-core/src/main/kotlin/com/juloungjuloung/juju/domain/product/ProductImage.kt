package com.juloungjuloung.juju.domain.product

data class ProductImage(
    val product: Product,
    val imageUrl: String,
    val isPrimary: Boolean
)