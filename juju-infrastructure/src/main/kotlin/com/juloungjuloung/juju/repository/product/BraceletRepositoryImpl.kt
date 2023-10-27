package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class BraceletRepositoryImpl(
    private val delegate: BraceletJpaRepository
) : BraceletRepository {

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Bracelet> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size)).stream()
            .map { it.toDomain() }
            .toList()
    }
}