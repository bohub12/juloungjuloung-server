package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.domain.product.filterNotPersisted
import com.juloungjuloung.juju.domain.product.filterPersisted
import com.juloungjuloung.juju.domain.product.repository.ProductColorRepository
import com.juloungjuloung.juju.domain.product.vo.UpsertProductColorVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductColorService(
    private val productColorRepository: ProductColorRepository
) {

    fun findByProduct(productId: Long): List<ProductColor> {
        return productColorRepository.findByProduct(productId)
    }

    @Transactional
    fun deleteAll(productColorIds: List<Long>): Boolean {
        validateDeleteCondition(productColorIds)

        productColorRepository.deleteAll(productColorIds)
        return true
    }

    private fun validateDeleteCondition(productColorIds: List<Long>) {
        findByIdsOrException(productColorIds)
    }

    private fun findByIdsOrException(productColorIds: List<Long>): List<ProductColor> {
        val productColors = productColorRepository.findByIds(productColorIds)
        if (productColors.size != productColorIds.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }

        return productColors
    }

    @Transactional
    fun upsertAll(upsertProductColorVO: UpsertProductColorVO): List<Long> {
        val productColors = upsertProductColorVO.toDomain()

        validateProductColorIds(productColors.filterPersisted().map { it.id })

        return productColorRepository.updateAll(productColors.filterPersisted())
            .plus(productColorRepository.saveAll(productColors.filterNotPersisted()))
    }

    private fun validateProductColorIds(productColorIds: List<Long>) {
        val persistedProductColors = productColorRepository.findByIds(productColorIds)

        if (productColorIds.size != persistedProductColors.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }
    }
}