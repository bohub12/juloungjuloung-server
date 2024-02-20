package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.CreatePreSignedUrlServiceFacade
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.dto.product.request.ChangeProductThumbnailImageRequest
import com.juloungjuloung.juju.dto.product.request.DeleteProductImageRequest
import com.juloungjuloung.juju.dto.product.request.SaveProductImageRequest
import com.juloungjuloung.juju.dto.product.response.GetPreSignedUrlResponse
import com.juloungjuloung.juju.dto.product.response.ProductImageResponse
import com.juloungjuloung.juju.objectmapper.toResponse
import com.juloungjuloung.juju.objectmapper.toSaveVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 이미지 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/images")
class ProductImageController(
    private val createPreSignedUrlServiceFacade: CreatePreSignedUrlServiceFacade,
    private val productImageService: ProductImageService
) {

    @GetMapping
    fun readProductImages(@RequestParam productId: Long): ApiResponse<List<ProductImageResponse>> {
        return success(productImageService.read(productId).map { toResponse(it) })
    }

    @PostMapping
    fun saveProductImages(
        @RequestBody saveProductImageRequest: SaveProductImageRequest
    ): ApiResponse<List<Long>> {
        return success(productImageService.saveAll(toSaveVO(saveProductImageRequest)))
    }

    @DeleteMapping
    fun deleteProductImages(
        @RequestBody deleteProductImageRequest: DeleteProductImageRequest
    ): ApiResponse<Boolean> {
        return success(productImageService.delete(deleteProductImageRequest.productImageIds))
    }

    @PostMapping("/pre-signed-url")
    fun createPreSignedUrl(): ApiResponse<GetPreSignedUrlResponse> {
        return success(toResponse(createPreSignedUrlServiceFacade.getPreSignedUrl()))
    }

    @PutMapping("change-thumbnail")
    fun changeThumbnailImageOfProduct(
        @RequestBody request: ChangeProductThumbnailImageRequest
    ): ApiResponse<Long> {
        return success(productImageService.changeThumbnail(request.productId, request.thumbnailImageId))
    }
}