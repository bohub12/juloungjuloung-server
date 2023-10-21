package com.juloungjuloung.juju.infrastructure.repository.product

import com.juloungjuloung.juju.domain.product.ring.Ring
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface RingRepository : JpaRepository<Ring, Long> {
    fun findAllByOrderByCreatedAt(pageable: Pageable): List<Ring>
}