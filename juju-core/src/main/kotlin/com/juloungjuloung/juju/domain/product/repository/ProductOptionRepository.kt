package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductOption

interface ProductOptionRepository {
    fun findByIds(productOptionIds: List<Long>): List<ProductOption>
    fun findAllByProductOptionCategoryIds(productOptionCategoryIds: List<Long>): List<ProductOption>
    fun saveAll(productOptions: List<ProductOption>): List<Long>
    fun updateAll(productOptions: List<ProductOption>): List<Long>
}