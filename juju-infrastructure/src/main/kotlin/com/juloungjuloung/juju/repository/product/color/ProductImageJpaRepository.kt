package com.juloungjuloung.juju.repository.product.color

import com.juloungjuloung.juju.entity.product.ProductImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageJpaRepository : JpaRepository<ProductImageEntity, Long> {
    fun findByProductId(productId: Long): List<ProductImageEntity>
}