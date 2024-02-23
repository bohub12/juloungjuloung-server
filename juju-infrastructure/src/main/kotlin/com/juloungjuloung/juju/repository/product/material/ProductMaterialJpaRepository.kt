package com.juloungjuloung.juju.repository.product.material

import com.juloungjuloung.juju.entity.product.ProductMaterialEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductMaterialJpaRepository : JpaRepository<ProductMaterialEntity, Long> {
    fun findAllByIdInAndDeletedFalse(productMaterialIds: List<Long>): List<ProductMaterialEntity>

    fun findAllByProductIdAndDeletedFalse(productId: Long): List<ProductMaterialEntity>
}