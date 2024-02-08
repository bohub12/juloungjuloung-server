package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.domain.product.service.ProductColorService
import com.juloungjuloung.juju.dto.product.request.DeleteProductColorRequest
import com.juloungjuloung.juju.dto.product.request.SaveProductColorRequest
import com.juloungjuloung.juju.dto.product.response.ProductColorResponse
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

@Tag(name = "상품 색상 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/colors")
class ProductColorController(
    private val productColorService: ProductColorService
) {

    @GetMapping
    fun readProductColors(@RequestParam productId: Long): ApiResponse<List<ProductColorResponse>> {
        return success(productColorService.findByProduct(productId).map { toResponse(it) })
    }

    @PostMapping
    fun saveProductColors(
        @RequestBody saveProductColorRequest: SaveProductColorRequest
    ): ApiResponse<List<Long>> {
        return success(productColorService.saveAll(toSaveVO(saveProductColorRequest)))
    }

    @DeleteMapping
    fun deleteProductColors(
        @RequestBody deleteProductColorRequest: DeleteProductColorRequest
    ): ApiResponse<Boolean> {
        return success(productColorService.deleteAll(deleteProductColorRequest.productColorIds))
    }
}