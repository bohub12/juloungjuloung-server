package com.juloungjuloung.juju.application.adminapi.product

import com.juloungjuloung.juju.application.product.BraceletService
import com.juloungjuloung.juju.presentation.adminapi.product.dto.BraceletDetailRes
import org.springdoc.core.converters.models.Pageable
import org.springframework.stereotype.Service

@Service
class AdminProductFacade(
    val braceletService: BraceletService
) {
    fun readBracelets(pageable: Pageable): List<BraceletDetailRes> {
        return braceletService.readBracelets(pageable.page, pageable.size).stream()
            .map { BraceletDetailRes.of(it) }
            .toList()
    }
}