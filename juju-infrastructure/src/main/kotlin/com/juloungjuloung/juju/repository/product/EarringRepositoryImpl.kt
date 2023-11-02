package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Earring
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class EarringRepositoryImpl(
    private val delegate: EarringJpaRepository
) : EarringRepository {

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Earring> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size)).stream()
            .map { it.toDomain() }
            .toList()
    }
}