package com.juloungjuloung.juju.infrastructure.repository

import com.juloungjuloung.juju.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>