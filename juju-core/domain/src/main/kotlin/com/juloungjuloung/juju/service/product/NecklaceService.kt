package com.juloungjuloung.juju.service.product

import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.repository.product.NecklaceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NecklaceService(
    private val necklaceRepository: NecklaceRepository
) {
    fun readNecklaces(page: Int, size: Int): List<Necklace> {
        return necklaceRepository.findAllByOrderByCreatedAt(page, size)
    }
}