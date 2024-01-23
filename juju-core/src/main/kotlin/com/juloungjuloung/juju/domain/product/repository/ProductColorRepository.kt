package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductColor

interface ProductColorRepository {
    fun findByProduct(productId: Long): List<ProductColor>

    fun saveAll(productColors: List<ProductColor>): List<Long>
}