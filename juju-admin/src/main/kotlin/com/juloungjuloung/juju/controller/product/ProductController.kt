package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.dto.product.BraceletDetailRes
import com.juloungjuloung.juju.dto.product.EarringDetailRes
import com.juloungjuloung.juju.dto.product.NecklaceDetailRes
import com.juloungjuloung.juju.dto.product.RingDetailRes
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import com.juloungjuloung.juju.servicefacade.product.ProductServiceFacade
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
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
    fun readProducts(
        @Valid
        @Parameter(hidden = true)
        pageable: Pageable
    ): ApiResponse<List<BraceletDetailRes>> {
        return success(productFacade.readBracelets(pageable.page, pageable.size))
    }

    @GetMapping("/earrings")
    @PageableAsQueryParam
    fun readEarrings(
        @Valid
        @Parameter(hidden = true)
        pageable: Pageable
    ): ApiResponse<List<EarringDetailRes>> {
        return success(productFacade.readEarrings(pageable.page, pageable.size))
    }

    @GetMapping("/necklaces")
    @PageableAsQueryParam
    fun readNecklaces(
        @Valid
        @Parameter(hidden = true)
        pageable: Pageable
    ): ApiResponse<List<NecklaceDetailRes>> {
        return success(productFacade.readNecklaces(pageable.page, pageable.size))
    }

    @GetMapping("/rings")
    @PageableAsQueryParam
    fun readRings(
        @Valid
        @Parameter(hidden = true)
        pageable: Pageable
    ): ApiResponse<List<RingDetailRes>> {
        return success(productFacade.readRings(pageable.page, pageable.size))
    }
}