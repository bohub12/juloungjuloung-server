package com.juloungjuloung.juju.servicefacade.product

import com.juloungjuloung.juju.dto.product.BraceletDetailRes
import com.juloungjuloung.juju.dto.product.RingDetailRes
import com.juloungjuloung.juju.service.product.BraceletService
import com.juloungjuloung.juju.service.product.RingService
import org.springframework.stereotype.Service

@Service
class ProductServiceFacade(
    private val braceletService: BraceletService,
    private val ringService: RingService
) {
    fun readBracelets(page: Int, size: Int): List<BraceletDetailRes> {
        return braceletService.readBracelets(page, size).stream()
            .map { BraceletDetailRes.of(it) }
            .toList()
    }

    fun readRings(page: Int, size: Int): List<RingDetailRes> {
        return ringService.readRings(page, size).stream()
            .map { RingDetailRes.of(it) }
            .toList()
    }
}