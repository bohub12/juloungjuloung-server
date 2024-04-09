package com.juloungjuloung.juju.controller.product

import com.juloungjuloung.juju.application.facade.product.ProductOptionServiceFacade
import com.juloungjuloung.juju.dto.product.request.UpsertProductOptionRequest
import com.juloungjuloung.juju.objectmapper.toUpsertVO
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.success
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상품 옵션 컨트롤러")
@RestController
@RequestMapping("/admin/api/v1/products/options")
class ProductOptionController(
    private val productOptionServiceFacade: ProductOptionServiceFacade
) {
    @PostMapping
    fun upsertProductOptions(
        @RequestBody request: UpsertProductOptionRequest
    ): ApiResponse<Long> {
        return success(productOptionServiceFacade.upsertProductOptions(toUpsertVO(request)))
    }

//    @DeleteMapping
//    fun deleteProductOption(
//        @RequestBody request: DeleteProductOptionRequest
//    ): ApiResponse<Boolean> {
//        TODO()
//        return success(productOptionServiceFacade.deleteProductOptions())
//    }
}