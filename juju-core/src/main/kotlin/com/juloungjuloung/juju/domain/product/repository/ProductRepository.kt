package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.Product

interface ProductRepository {
    fun findById(productId: Long): Product
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Product>
    fun changePrimaryImage(product: Product): Long
    fun count(): Long
}