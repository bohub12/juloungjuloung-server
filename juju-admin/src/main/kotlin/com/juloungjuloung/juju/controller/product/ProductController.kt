package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.dto.product.BraceletDetailRes
import com.juloungjuloung.juju.dto.product.RingDetailRes
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import com.juloungjuloung.juju.servicefacade.product.ProductServiceFacade
import io.swagger.v3.oas.annotations.Parameter
import org.springdoc.core.converters.models.Pageable
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/v1/products")
class ProductController(
    val productFacade: ProductServiceFacade
) {

    @GetMapping("/bracelets")
    @PageableAsQueryParam
    fun readProducts(@Parameter(hidden = true) pageable: Pageable): ApiResponse<List<BraceletDetailRes>> {
        return success(productFacade.readBracelets(pageable.page, pageable.size))
    }

    @GetMapping("/earrings")
    @PageableAsQueryParam
    fun readEarrings(pageable: Pageable) {
    }

    @GetMapping("/necklaces")
    @PageableAsQueryParam
    fun readNecklaces(pageable: Pageable) {
    }

    @GetMapping("/rings")
    @PageableAsQueryParam
    fun readRings(@Parameter(hidden = true) pageable: Pageable): ApiResponse<List<RingDetailRes>> {
        return success(productFacade.readRings(pageable.page, pageable.size))
    }
}