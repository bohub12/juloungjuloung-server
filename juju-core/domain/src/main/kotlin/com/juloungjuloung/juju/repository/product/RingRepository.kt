package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Ring

interface RingRepository {
    fun findById(id: Long): Ring
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Ring>
    fun save(ring: Ring): Boolean
    fun update(ring: Ring): Boolean
}