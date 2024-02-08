package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.entity.product.impl.RingEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface RingJpaRepository : JpaRepository<RingEntity, Long> {
    // TODO : 삭제 기능 추가될 때, 논리적으로 삭제되지 않은 로우만 조회하도록 수정
    fun findAllByOrderByCreatedAt(pageable: Pageable): List<RingEntity>
}