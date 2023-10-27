package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Ring
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class RingRepositoryImpl(
    private val delegate: RingJpaRepository
) : RingRepository {

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Ring> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size)).stream()
            .map { it.toDomain() }
            .toList()
    }
}