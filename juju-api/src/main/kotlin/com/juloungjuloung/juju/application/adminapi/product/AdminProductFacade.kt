package com.juloungjuloung.juju.application.adminapi.product

import com.juloungjuloung.juju.application.product.BraceletService
import com.juloungjuloung.juju.application.product.RingService
import com.juloungjuloung.juju.presentation.adminapi.product.dto.BraceletDetailRes
import com.juloungjuloung.juju.presentation.adminapi.product.dto.RingDetailRes
import org.springdoc.core.converters.models.Pageable
import org.springframework.stereotype.Service

@Service
class AdminProductFacade(
    val braceletService: BraceletService,
    val ringService: RingService
) {
    fun readBracelets(pageable: Pageable): List<BraceletDetailRes> {
        return braceletService.readBracelets(pageable.page, pageable.size).stream()
            .map { BraceletDetailRes.of(it) }
            .toList()
    }

    fun readRings(pageable: Pageable): List<RingDetailRes> {
        return ringService.readRings(pageable.page, pageable.size).stream()
            .map { RingDetailRes.of(it) }
            .toList()
    }
}