package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.ProductOptionInfo
import com.juloungjuloung.juju.domain.product.service.ProductOptionCategoryService
import com.juloungjuloung.juju.domain.product.service.ProductOptionService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.product.vo.UpsertProductOptionVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductOptionServiceFacade(
    private val productService: ProductServiceImpl,
    private val productOptionService: ProductOptionService,
    private val productOptionCategoryService: ProductOptionCategoryService
) {

    @Transactional
    fun upsertProductOptions(upsertProductOptionVO: UpsertProductOptionVO): Long {
        val productOptionInfo = upsertProductOptionVO.toDomainInfo()

        validateUpsertCondition(productOptionInfo)

        val productOptionCategoryId = productOptionCategoryService.upsert(productOptionInfo.optionCategory)
        productOptionInfo.updateProductOptionCategoryId(productOptionCategoryId)

        productOptionService.upsert(productOptionInfo.options)

        return productOptionCategoryId
    }

    private fun validateUpsertCondition(productOptionInfo: ProductOptionInfo) {
        validateProductId(productOptionInfo.optionCategory.productId)
        if (productOptionInfo.isPersistedProductOptionCategory()) {
            validateProductOptionCategoryId(productOptionInfo.optionCategory.id)
        }
        validateProductOptionIds(productOptionInfo.filterPersistedOption().map { it.id })
    }

    private fun validateProductId(productId: Long) {
        productService.readById(productId)
    }

    private fun validateProductOptionCategoryId(productOptionCategoryId: Long) {
        productOptionCategoryService.readById(productOptionCategoryId)
    }

    private fun validateProductOptionIds(productOptionIds: List<Long>) {
        val productOptions = productOptionService.readByIds(productOptionIds)
        if (productOptions.size != productOptionIds.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }
    }
}