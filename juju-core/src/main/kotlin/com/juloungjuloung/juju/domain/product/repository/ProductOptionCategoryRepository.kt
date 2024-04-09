package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductOptionCategory

interface ProductOptionCategoryRepository {
    fun findById(productOptionCategoryId: Long): ProductOptionCategory

    fun findAllByProductId(productId: Long): List<ProductOptionCategory>

    fun save(productOptionCategory: ProductOptionCategory): Long

    fun update(productOptionCategory: ProductOptionCategory): Long
}