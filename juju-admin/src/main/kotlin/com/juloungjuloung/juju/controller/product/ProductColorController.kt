package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.ProductColorServiceFacade
import com.juloungjuloung.juju.dto.product.request.UpsertProductColorRequest
import com.juloungjuloung.juju.dto.product.response.ProductColorResponse
import com.juloungjuloung.juju.objectmapper.toResponse
import com.juloungjuloung.juju.objectmapper.toUpsertVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 색상 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/colors")
class ProductColorController(
    private val productColorServiceFacade: ProductColorServiceFacade
) {

    @GetMapping
    fun readProductColors(@RequestParam productId: Long): ApiResponse<List<ProductColorResponse>> {
        return success(productColorServiceFacade.findByProduct(productId).map { toResponse(it) })
    }

    @PutMapping
    fun upsertProductColors(
        @RequestBody upsertProductColorRequest: UpsertProductColorRequest
    ): ApiResponse<List<Long>> {
        return success(productColorServiceFacade.upsertProductColors(toUpsertVO(upsertProductColorRequest)))
    }
}