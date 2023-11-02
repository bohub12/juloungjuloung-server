package com.juloungjuloung.juju.servicefacade.product

import com.juloungjuloung.juju.dto.product.BraceletDetailRes
import com.juloungjuloung.juju.dto.product.EarringDetailRes
import com.juloungjuloung.juju.dto.product.NecklaceDetailRes
import com.juloungjuloung.juju.dto.product.RingDetailRes
import com.juloungjuloung.juju.s3.AwsS3Service
import com.juloungjuloung.juju.service.product.BraceletService
import com.juloungjuloung.juju.service.product.EarringService
import com.juloungjuloung.juju.service.product.NecklaceService
import com.juloungjuloung.juju.service.product.RingService
import org.springframework.stereotype.Service

@Service
class ProductServiceFacade(
    private val braceletService: BraceletService,
    private val ringService: RingService,
    private val earringService: EarringService,
    private val necklaceService: NecklaceService,
    private val awsS3Service: AwsS3Service
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

    fun readEarrings(page: Int, size: Int): List<EarringDetailRes> {
        return earringService.readEarrings(page, size).stream()
            .map { EarringDetailRes.of(it) }
            .toList()
    }

    fun readNecklaces(page: Int, size: Int): List<NecklaceDetailRes> {
        return necklaceService.readNecklaces(page, size).stream()
            .map { NecklaceDetailRes.of(it) }
            .toList()
    }
}