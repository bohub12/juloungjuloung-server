package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.ProductImageServiceFacade
import com.juloungjuloung.juju.dto.product.response.GetPreSignedUrlResponse
import com.juloungjuloung.juju.objectmapper.ProductImageResponseMapper.Companion.toResponse
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/images")
class ProductImageController(
    private val productImageServiceFacade: ProductImageServiceFacade
) {

    @PostMapping
    fun createPreSignedUrl(): ApiResponse<GetPreSignedUrlResponse> {
        return success(toResponse(productImageServiceFacade.getPreSignedUrl()))
    }
}