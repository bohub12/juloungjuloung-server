package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductColor

interface ProductColorRepository {
    fun findByIds(productColorIds: List<Long>): List<ProductColor>

    fun findByProduct(productId: Long): List<ProductColor>

    fun saveAll(productColors: List<ProductColor>): List<Long>

    fun updateAll(productColors: List<ProductColor>): List<Long>

    fun deleteAll(productColorIds: List<Long>)
}