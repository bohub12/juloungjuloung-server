package com.juloungjuloung.juju.application.product

import com.juloungjuloung.juju.domain.product.bracelet.Bracelet
import com.juloungjuloung.juju.infrastructure.repository.product.BraceletRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BraceletService(
    val braceletRepository: BraceletRepository
) {

    fun readBracelets(page: Int, size: Int): List<Bracelet> {
        return braceletRepository.findAllByOrderByCreatedAt(
            PageRequest.of(page, size)
        )
    }
}