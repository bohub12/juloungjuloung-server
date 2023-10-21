package com.juloungjuloung.juju.application.product

import com.juloungjuloung.juju.domain.product.ring.Ring
import com.juloungjuloung.juju.infrastructure.repository.product.RingRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RingService(
    val ringRepository: RingRepository
) {

    fun readRings(page: Int, size: Int): List<Ring> {
        return ringRepository.findAllByOrderByCreatedAt(PageRequest.of(page, size))
    }
}