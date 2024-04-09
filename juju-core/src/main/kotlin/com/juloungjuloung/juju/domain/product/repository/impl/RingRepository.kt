package com.juloungjuloung.juju.domain.product.repository.impl

import com.juloungjuloung.juju.domain.product.impl.Ring

interface RingRepository {
    fun findById(id: Long): Ring

    fun findAllByOrderByCreatedAt(
        page: Int,
        size: Int
    ): List<Ring>

    fun count(): Long

    fun save(ring: Ring): Long

    fun update(ring: Ring): Long
}