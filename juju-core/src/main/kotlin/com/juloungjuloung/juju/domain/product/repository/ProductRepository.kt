package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.Product

interface ProductRepository {
    fun findById(productId: Long): Product
}