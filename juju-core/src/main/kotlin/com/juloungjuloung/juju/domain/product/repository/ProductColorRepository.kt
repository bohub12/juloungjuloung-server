package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductColor

interface ProductColorRepository {
    fun saveAll(productColors: List<ProductColor>): List<Long>
}