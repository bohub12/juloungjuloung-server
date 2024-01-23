package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.domain.product.combineForValidation
import com.juloungjuloung.juju.domain.product.repository.ProductColorRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductColorVO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductColorService(
    private val productRepository: ProductRepository,
    private val productColorRepository: ProductColorRepository
) {

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

    fun findByProduct(productId: Long): List<ProductColor> {
        return productColorRepository.findByProduct(productId)
    }

    private fun findProductOrException(productId: Long) {
        productRepository.findById(productId)
    }
}