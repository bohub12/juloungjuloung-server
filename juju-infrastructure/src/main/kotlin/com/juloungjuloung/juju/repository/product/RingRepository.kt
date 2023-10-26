package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.entity.product.impl.RingEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface RingRepository : JpaRepository<RingEntity, Long> {
    fun findAllByOrderByCreatedAt(pageable: Pageable): List<RingEntity>
}