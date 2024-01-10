package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.ProductImages

interface ProductImageRepository {
    fun findByProduct(productId: Long): List<ProductImage>

    fun save(productImage: ProductImage): Long

    fun saveAll(productImages: ProductImages): List<Long>

    fun delete(productImageId: Long)

    fun deleteAll(productImageIds: List<Long>)
}