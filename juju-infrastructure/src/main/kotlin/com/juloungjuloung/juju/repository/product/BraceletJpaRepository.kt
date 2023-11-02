package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.entity.product.impl.BraceletEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BraceletJpaRepository : JpaRepository<BraceletEntity, Long> {
    fun findAllByOrderByCreatedAt(pageable: Pageable): List<BraceletEntity>
}