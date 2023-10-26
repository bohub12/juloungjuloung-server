package com.juloungjuloung.juju.service.product

import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.repository.product.RingRepository
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
            .stream()
            .map { Ring.of(it) }
            .toList()
    }
}