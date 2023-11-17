package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.common.response.ApiResponse
import com.juloungjuloung.juju.common.response.ApiResponse.Companion.success
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import com.juloungjuloung.juju.service.facade.product.ProductServiceFacade
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products")
class ProductController(
    val productFacade: ProductServiceFacade
) {

    @GetMapping
    fun readProducts(
        @RequestParam productType: String,
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int
    ): ApiResponse<List<ProductResponse>> {
        return success(productFacade.readProducts(productType, page, size))
    }
}