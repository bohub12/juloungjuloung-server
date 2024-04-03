package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.CreatePreSignedUrlServiceFacade
import com.juloungjuloung.juju.application.facade.product.ProductImageServiceFacade
import com.juloungjuloung.juju.dto.product.request.UpsertProductImageRequest
import com.juloungjuloung.juju.dto.product.response.GetPreSignedUrlResponse
import com.juloungjuloung.juju.dto.product.response.ProductImageResponse
import com.juloungjuloung.juju.objectmapper.toResponse
import com.juloungjuloung.juju.objectmapper.toUpsertVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 이미지 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/images")
class ProductImageController(
    private val productImageServiceFacade: ProductImageServiceFacade,
    private val createPreSignedUrlServiceFacade: CreatePreSignedUrlServiceFacade
) {

    @GetMapping
    fun readProductImages(@RequestParam productId: Long): ApiResponse<List<ProductImageResponse>> {
        return success(productImageServiceFacade.readByProduct(productId).map { toResponse(it) })
    }

    @PostMapping("/pre-signed-url")
    fun createPreSignedUrl(): ApiResponse<GetPreSignedUrlResponse> {
        return success(toResponse(createPreSignedUrlServiceFacade.getPreSignedUrl()))
    }

    @PostMapping
    fun upsertProductImages(
        @RequestBody upsertProductImageRequest: UpsertProductImageRequest
    ): ApiResponse<List<Long>> {
        return success(productImageServiceFacade.upsertProductImages(toUpsertVO(upsertProductImageRequest)))
    }
}