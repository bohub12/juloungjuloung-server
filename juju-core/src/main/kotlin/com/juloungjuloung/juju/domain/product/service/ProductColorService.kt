package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.repository.ProductColorRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductColorVO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductColorService(
    private val productColorRepository: ProductColorRepository
) {

    @Transactional
    fun saveAll(saveProductColorVO: SaveProductColorVO): List<Long> {
        validateSaveCondition(saveProductColorVO.productId)

        return productColorRepository.saveAll(saveProductColorVO.toDomain())
    }

    private fun validateSaveCondition(productId: Long) {
        // TODO validation - product id
    }
}