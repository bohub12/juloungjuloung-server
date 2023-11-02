package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.entity.product.impl.EarringEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface EarringJpaRepository : JpaRepository<EarringEntity, Long> {
    fun findAllByOrderByCreatedAt(pageable: Pageable): List<EarringEntity>
}