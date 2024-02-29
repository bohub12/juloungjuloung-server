package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.ProductMaterialServiceFacade
import com.juloungjuloung.juju.dto.product.request.UpsertProductMaterialRequest
import com.juloungjuloung.juju.dto.product.response.ProductMaterialResponse
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

@Tag(name = "상품 재질 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/materials")
class ProductMaterialController(
    private val productMaterialServiceFacade: ProductMaterialServiceFacade
) {

    @GetMapping
    fun readProductMaterials(
        @RequestParam productId: Long
    ): ApiResponse<List<ProductMaterialResponse>> {
        return success(productMaterialServiceFacade.readProductMaterialsByProduct(productId).map { toResponse(it) })
    }

    @PutMapping
    fun upsertProductMaterials(
        @RequestBody upsertProductMaterialRequest: UpsertProductMaterialRequest
    ): ApiResponse<List<Long>> {
        return success(productMaterialServiceFacade.upsertAll(toUpsertVO(upsertProductMaterialRequest)))
    }
}