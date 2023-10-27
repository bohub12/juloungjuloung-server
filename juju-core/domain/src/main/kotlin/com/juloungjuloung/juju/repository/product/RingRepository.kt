package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Ring

interface RingRepository {
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Ring>
}