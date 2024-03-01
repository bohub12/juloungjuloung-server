package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.dto.product.request.UpsertProductOptionRequest
import com.juloungjuloung.juju.response.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 옵션 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/options")
class ProductOptionController(
    private val productOptionServiceFacade: ProductOptionServiceFacade
) {

    @PutMapping
    fun upsertProductOptions(
        @RequestBody upsertProductOptionRequest: UpsertProductOptionRequest
    ): ApiResponse<List<Long>> {
        return ApiResponse.Companion.success(
            productOptionServiceFacade.upsertProductOptions(upsertProductOptionRequest)
        )
    }
}