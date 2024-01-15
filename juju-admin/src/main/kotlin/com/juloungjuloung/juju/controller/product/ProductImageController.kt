package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.ProductImageServiceFacade
import com.juloungjuloung.juju.dto.product.request.SaveProductImageRequest
import com.juloungjuloung.juju.dto.product.response.GetPreSignedUrlResponse
import com.juloungjuloung.juju.objectmapper.toResponse
import com.juloungjuloung.juju.objectmapper.toSaveVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 이미지 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/images")
class ProductImageController(
    private val productImageServiceFacade: ProductImageServiceFacade
) {

    @PostMapping("/pre-signed-url")
    fun createPreSignedUrl(): ApiResponse<GetPreSignedUrlResponse> {
        return success(toResponse(productImageServiceFacade.getPreSignedUrl()))
    }

    @PostMapping
    fun saveAll(
        @RequestBody saveProductImageRequest: SaveProductImageRequest
    ): ApiResponse<List<Long>> {
        return success(productImageServiceFacade.saveAll(toSaveVO(saveProductImageRequest)))
    }
}