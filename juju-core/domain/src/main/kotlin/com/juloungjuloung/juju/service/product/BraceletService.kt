package com.juloungjuloung.juju.service.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.repository.product.BraceletRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BraceletService(
    val braceletRepository: BraceletRepository
) {

    fun readBracelets(page: Int, size: Int): List<Bracelet> {
        return braceletRepository.findAllByOrderByCreatedAt(page, size)
    }
}