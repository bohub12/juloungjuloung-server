package com.juloungjuloung.juju.repository.product.image

import com.juloungjuloung.juju.entity.product.ProductImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageJpaRepository : JpaRepository<ProductImageEntity, Long> {
    fun findByProductIdAndDeletedFalse(productId: Long): List<ProductImageEntity>

    fun findAllByIdInAndDeletedFalse(productImageIds: List<Long>): List<ProductImageEntity>
}