package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.entity.product.impl.NecklaceEntity
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

    override fun save(necklace: Necklace): Boolean {
        delegate.save(NecklaceEntity.of(necklace))
        return true
    }
}