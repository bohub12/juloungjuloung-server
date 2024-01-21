package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductImage

interface ProductImageRepository {
    fun findByProduct(productId: Long): List<ProductImage>

    fun findById(productImageId: Long): ProductImage

    fun findByIds(productImageIds: List<Long>): List<ProductImage>

    fun save(productImage: ProductImage): Long

    fun saveAll(productImages: List<ProductImage>): List<Long>

    fun deleteAll(productImageIds: List<Long>)

    fun update(productImages: List<ProductImage>)
}