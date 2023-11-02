package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.entity.product.impl.NecklaceEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface NecklaceJpaRepository : JpaRepository<NecklaceEntity, Long> {
    fun findAllByOrderByCreatedAt(pageable: Pageable): List<NecklaceEntity>
}