package com.juloungjuloung.juju.presentation.adminapi.product

import com.juloungjuloung.juju.application.adminapi.product.AdminProductFacade
import com.juloungjuloung.juju.presentation.ApiResponse
import com.juloungjuloung.juju.presentation.ApiResponse.Companion.success
import com.juloungjuloung.juju.presentation.adminapi.product.dto.BraceletDetailRes
import io.swagger.v3.oas.annotations.Parameter
import org.springdoc.core.converters.models.Pageable
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/v1/products")
class AdminProductController(
    val adminProductFacade: AdminProductFacade
) {

    @GetMapping("/bracelets")
    @PageableAsQueryParam
    fun readProducts(@Parameter(hidden = true) pageable: Pageable): ApiResponse<List<BraceletDetailRes>> {
        return success(adminProductFacade.readBracelets(pageable))
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
    fun readRings(pageable: Pageable) {
    }
}