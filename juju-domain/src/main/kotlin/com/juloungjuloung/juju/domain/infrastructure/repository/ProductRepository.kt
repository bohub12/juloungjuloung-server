package com.juloungjuloung.juju.domain.infrastructure.repository

import com.juloungjuloung.juju.domain.domain.product.Product
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {

    fun findProducts(pageable: Pageable): List<Product>
}