package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Earring

interface EarringRepository {
    fun findById(id: Long): Earring
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Earring>
    fun save(earring: Earring): Long
    fun update(earring: Earring): Long
}