package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Necklace

interface NecklaceRepository {
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Necklace>
}