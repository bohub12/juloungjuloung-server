package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.entity.product.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductEntity, Long>