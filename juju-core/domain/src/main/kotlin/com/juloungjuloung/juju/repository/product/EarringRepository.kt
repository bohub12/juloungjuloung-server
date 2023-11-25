package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Earring

interface EarringRepository {
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Earring>
    fun save(earring: Earring): Boolean
}