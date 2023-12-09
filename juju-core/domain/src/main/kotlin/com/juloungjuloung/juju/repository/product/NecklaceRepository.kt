package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Necklace

interface NecklaceRepository {
    fun findById(id: Long): Necklace
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Necklace>
    fun save(necklace: Necklace): Long
    fun update(necklace: Necklace): Long
}