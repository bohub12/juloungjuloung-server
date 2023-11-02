package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Necklace
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class NecklaceRepositoryImpl(
    private val delegate: NecklaceJpaRepository
) : NecklaceRepository {

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Necklace> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size)).stream()
            .map { it.toDomain() }
            .toList()
    }
}