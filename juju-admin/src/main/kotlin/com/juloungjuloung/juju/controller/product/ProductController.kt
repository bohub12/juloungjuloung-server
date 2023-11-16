package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.common.response.ApiResponse
import com.juloungjuloung.juju.common.response.ApiResponse.Companion.success
import com.juloungjuloung.juju.dto.product.BraceletDetailRes
import com.juloungjuloung.juju.dto.product.EarringDetailRes
import com.juloungjuloung.juju.dto.product.NecklaceDetailRes
import com.juloungjuloung.juju.dto.product.RingDetailRes
import com.juloungjuloung.juju.servicefacade.product.ProductServiceFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/v1/products")
class ProductController(
    val productFacade: ProductServiceFacade
) {

    @GetMapping("/bracelets")
    fun readProducts(
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int
    ): ApiResponse<List<BraceletDetailRes>> {
        return success(productFacade.readBracelets(page, size))
    }

    @GetMapping("/earrings")
    fun readEarrings(
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int
    ): ApiResponse<List<EarringDetailRes>> {
        return success(productFacade.readEarrings(page, size))
    }

    @GetMapping("/necklaces")
    fun readNecklaces(
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int
    ): ApiResponse<List<NecklaceDetailRes>> {
        return success(productFacade.readNecklaces(page, size))
    }

    @GetMapping("/rings")
    fun readRings(
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int
    ): ApiResponse<List<RingDetailRes>> {
        return success(productFacade.readRings(page, size))
    }
}