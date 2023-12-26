package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.impl.Bracelet

interface BraceletRepository {
    fun findById(id: Long): Bracelet
    fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Bracelet>
    fun save(bracelet: Bracelet): Long
    fun update(bracelet: Bracelet): Long
}