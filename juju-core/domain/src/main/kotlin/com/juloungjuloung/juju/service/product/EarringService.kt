package com.juloungjuloung.juju.service.product

import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.repository.product.EarringRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class EarringService(
    private val earringRepository: EarringRepository
) {
    fun readEarrings(page: Int, size: Int): List<Earring> {
        return earringRepository.findAllByOrderByCreatedAt(page, size)
    }
}