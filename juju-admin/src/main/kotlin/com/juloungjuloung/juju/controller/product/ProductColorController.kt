package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.domain.product.service.ProductColorService
import com.juloungjuloung.juju.dto.product.request.SaveProductColorRequest
import com.juloungjuloung.juju.objectmapper.toSaveVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 색상 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/colors")
class ProductColorController(
    private val productColorService: ProductColorService
) {

    @PostMapping
    fun saveAll(
        @RequestBody saveProductColorRequest: SaveProductColorRequest
    ): ApiResponse<List<Long>> {
        return success(productColorService.saveAll(toSaveVO(saveProductColorRequest)))
    }
}