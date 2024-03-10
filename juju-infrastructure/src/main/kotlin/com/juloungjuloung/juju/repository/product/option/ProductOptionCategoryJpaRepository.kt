package com.juloungjuloung.juju.repository.product.option

import com.juloungjuloung.juju.entity.product.ProductOptionCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionCategoryJpaRepository : JpaRepository<ProductOptionCategoryEntity, Long> {
    fun findAllByProductIdAndDeletedFalse(productId: Long): List<ProductOptionCategoryEntity>
}