package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.domain.product.filterNotPersisted
import com.juloungjuloung.juju.domain.product.filterPersisted
import com.juloungjuloung.juju.domain.product.repository.ProductMaterialRepository
import com.juloungjuloung.juju.domain.product.vo.UpsertProductMaterialVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductMaterialService(
    private val productMaterialRepository: ProductMaterialRepository
) {
    fun readProductMaterials(productId: Long): List<ProductMaterial> {
        return productMaterialRepository.findByProduct(productId)
    }

    @Transactional
    fun deleteAll(productMaterialIds: List<Long>): Boolean {
        validateDeleteCondition(productMaterialIds)

        productMaterialRepository.deleteAll(productMaterialIds)
        return true
    }

    private fun validateDeleteCondition(productMaterialIds: List<Long>) {
        val findProductMaterials = productMaterialRepository.findByIds(productMaterialIds)

        if (findProductMaterials.size != productMaterialIds.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }
    }

    @Transactional
    fun upsertAll(upsertProductMaterialVO: UpsertProductMaterialVO): List<Long> {
        val productMaterials = upsertProductMaterialVO.toDomain()

        validateProductMaterialIds(productMaterials.filterPersisted().map { it.id })

        return productMaterialRepository.updateAll(productMaterials.filterPersisted())
            .plus(productMaterialRepository.saveAll(productMaterials.filterNotPersisted()))
    }

    private fun validateProductMaterialIds(productMaterialIds: List<Long>) {
        val persistedProductMaterials = productMaterialRepository.findByIds(productMaterialIds)

        if (productMaterialIds.size != persistedProductMaterials.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }
    }
}