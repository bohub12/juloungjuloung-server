package com.juloungjuloung.juju.infrastructure.repository.product

import com.juloungjuloung.juju.domain.product.bracelet.Bracelet
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BraceletRepository : JpaRepository<Bracelet, Long> {
    fun findAllByOrderByCreatedAt(pageable: Pageable): List<Bracelet>
}