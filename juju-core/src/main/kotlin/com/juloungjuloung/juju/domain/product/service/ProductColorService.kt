package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.domain.product.combineForValidation
import com.juloungjuloung.juju.domain.product.repository.ProductColorRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductColorVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductColorService(
    private val productRepository: ProductRepository,
    private val productColorRepository: ProductColorRepository
) {

    fun findByProduct(productId: Long): List<ProductColor> {
        findProductOrException(productId)

        return productColorRepository.findByProduct(productId)
    }

    @Transactional
    fun saveAll(saveProductColorVO: SaveProductColorVO): List<Long> {
        validateSaveCondition(saveProductColorVO)

        return productColorRepository.saveAll(saveProductColorVO.toDomain())
    }

    private fun validateSaveCondition(saveProductColorVO: SaveProductColorVO) {
        findProductOrException(saveProductColorVO.productId)

        val productColors = findByProduct(saveProductColorVO.productId)
        val productColorsForSave = saveProductColorVO.toDomain()

        productColors.combineForValidation(productColorsForSave)
    }

    private fun findProductOrException(productId: Long) {
        productRepository.findById(productId)
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
}