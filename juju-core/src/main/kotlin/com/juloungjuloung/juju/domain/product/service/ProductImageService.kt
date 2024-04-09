package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.filterNotPersisted
import com.juloungjuloung.juju.domain.product.filterPersisted
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.vo.UpsertProductImageVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductImageService(
    private val productImageRepository: ProductImageRepository
) {
    fun readByProduct(productId: Long): List<ProductImage> {
        return productImageRepository.findByProduct(productId)
    }

    @Transactional
    fun upsert(upsertProductImageVO: UpsertProductImageVO): List<Long> {
        val productImages = upsertProductImageVO.toDomain()

        validateProductImageIds(productImages.filterPersisted().map { it.id })

        return productImageRepository.updateAll(productImages.filterPersisted())
            .plus(productImageRepository.saveAll(productImages.filterNotPersisted()))
    }

    private fun validateProductImageIds(productImageIds: List<Long>) {
        val persistedProductImages = productImageRepository.findByIds(productImageIds)

        if (productImageIds.size != persistedProductImages.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }
    }

    @Transactional
    fun deleteAll(productImageIds: List<Long>): Boolean {
        validateDeleteCondition(productImageIds)

        productImageRepository.deleteAll(productImageIds)
        return true
    }

    private fun validateDeleteCondition(productImageIds: List<Long>) {
        readByIdsOrException(productImageIds)
    }

    private fun readByIdsOrException(productImageIds: List<Long>): List<ProductImage> {
        val productImages = productImageRepository.findByIds(productImageIds)
        if (productImages.size != productImageIds.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }

        return productImages
    }
}