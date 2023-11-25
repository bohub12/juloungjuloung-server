package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet

interface BraceletRepository {
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Bracelet>
    fun save(bracelet: Bracelet): Boolean
}