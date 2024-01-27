package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.domain.product.service.ProductMaterialService
import com.juloungjuloung.juju.dto.product.request.DeleteProductMaterialRequest
import com.juloungjuloung.juju.dto.product.request.SaveProductMaterialRequest
import com.juloungjuloung.juju.dto.product.response.ProductMaterialResponse
import com.juloungjuloung.juju.objectmapper.toResponse
import com.juloungjuloung.juju.objectmapper.toSaveVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 재질 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/materials")
class ProductMaterialController(
    private val productMaterialService: ProductMaterialService
) {

    @GetMapping
    fun readProductMaterials(
        @RequestParam productId: Long
    ): ApiResponse<List<ProductMaterialResponse>> {
        return success(productMaterialService.readProductMaterials(productId).map { toResponse(it) })
    }

    @PostMapping
    fun saveAll(
        @RequestBody saveProductMaterialRequest: SaveProductMaterialRequest
    ): ApiResponse<List<Long>> {
        return success(productMaterialService.saveAll(toSaveVO(saveProductMaterialRequest)))
    }

    @DeleteMapping
    fun deleteAll(
        @RequestBody deleteProductMaterialRequest: DeleteProductMaterialRequest
    ): ApiResponse<Boolean> {
        return success(productMaterialService.deleteAll(deleteProductMaterialRequest.productMaterialIds))
    }
}