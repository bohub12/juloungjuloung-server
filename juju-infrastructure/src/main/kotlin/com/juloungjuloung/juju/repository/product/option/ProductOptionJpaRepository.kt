package com.juloungjuloung.juju.repository.product.option

import com.juloungjuloung.juju.entity.product.ProductOptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionJpaRepository : JpaRepository<ProductOptionEntity, Long>