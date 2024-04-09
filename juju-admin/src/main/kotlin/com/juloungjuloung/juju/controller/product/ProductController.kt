package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.ProductOptionServiceFacade
import com.juloungjuloung.juju.application.facade.product.ProductServiceFacade
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest
import com.juloungjuloung.juju.dto.product.request.UpdateProductRequest
import com.juloungjuloung.juju.dto.product.response.ProductDetailResponse
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.objectmapper.toResponse
import com.juloungjuloung.juju.objectmapper.toSaveVO
import com.juloungjuloung.juju.objectmapper.toUpdateVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import com.juloungjuloung.juju.response.PageResponse
import com.juloungjuloung.juju.utils.getTotalPageCount
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products")
class ProductController(
    private val productServiceFacade: ProductServiceFacade,
    private val productOptionServiceFacade: ProductOptionServiceFacade
) {
    @GetMapping
    fun readProducts(
        @RequestParam(required = false, defaultValue = "BASE") productType: ProductTypeEnum,
        @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "10") size: Int
    ): ApiResponse<List<ProductResponse>> {
        val productsWithCount = productServiceFacade.read(productType, page, size)
        val totalPageCount = getTotalPageCount(totalElementCount = productsWithCount.totalElementCount, pageSize = size)

        return success(
            productsWithCount.products.map { toResponse(it) }.toList(),
            PageResponse(page, size, totalPageCount)
        )
    }

    @GetMapping("detail")
    fun readProductDetail(
        @RequestParam(required = true) productId: Long
    ): ApiResponse<ProductDetailResponse> {
        val product = productServiceFacade.readById(productId)
        val productOptions = productOptionServiceFacade.readAllByProductId(productId)

        return success(ProductDetailResponse.from(product, productOptions))
    }

    @PostMapping
    fun saveProduct(
        @RequestBody saveProductRequest: SaveProductRequest
    ): ApiResponse<Long> {
        return success(productServiceFacade.save(toSaveVO(saveProductRequest)))
    }

    @PutMapping
    fun updateProduct(
        @RequestBody @Valid
        updateProductRequest: UpdateProductRequest
    ): ApiResponse<Long> {
        return success(productServiceFacade.update(toUpdateVO(updateProductRequest)))
    }
}