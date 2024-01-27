package com.juloungjuloung.juju.repository.product.color

import com.juloungjuloung.juju.entity.product.ProductColorEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductColorJpaRepository : JpaRepository<ProductColorEntity, Long> {
    fun findAllByIdInAndDeletedFalse(ids: List<Long>): List<ProductColorEntity>

    fun findAllByProductIdAndDeletedFalse(productId: Long): List<ProductColorEntity>
}