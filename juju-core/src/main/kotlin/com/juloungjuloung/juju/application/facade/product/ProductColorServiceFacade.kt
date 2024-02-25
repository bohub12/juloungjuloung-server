package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.domain.product.service.ProductColorService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.product.validate
import com.juloungjuloung.juju.domain.product.vo.UpsertProductColorVO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductColorServiceFacade(
    private val productColorService: ProductColorService,
    private val productService: ProductServiceImpl
) {

    fun findByProduct(productId: Long): List<ProductColor> {
        findProductOrException(productId)

        return productColorService.findByProduct(productId)
    }

    private fun findProductOrException(productId: Long) {
        productService.readById(productId)
    }

    @Transactional
    fun upsertProductColors(upsertProductColorVO: UpsertProductColorVO): List<Long> {
        val productColors = upsertProductColorVO.toDomain().validate()

        findProductOrException(upsertProductColorVO.productId)
        deletePersistedProductColorsExcludeRequest(upsertProductColorVO.productId, productColors.getProductColorIds())

        return productColorService.upsertAll(upsertProductColorVO)
    }

    private fun deletePersistedProductColorsExcludeRequest(productId: Long, requestedProductColorIds: List<Long>) {
        val persistedProductColors = findByProduct(productId).map { it.id }

        productColorService.deleteAll(persistedProductColors.filterNot { requestedProductColorIds.contains(it) })
    }
}