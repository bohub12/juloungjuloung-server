package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.domain.product.combineForValidation
import com.juloungjuloung.juju.domain.product.repository.ProductMaterialRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductMaterialVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductMaterialService(
    private val productRepository: ProductRepository,
    private val productMaterialRepository: ProductMaterialRepository
) {

    @Transactional
    fun saveAll(saveProductMaterialVO: SaveProductMaterialVO): List<Long> {
        validateSaveCondition(saveProductMaterialVO)

        return productMaterialRepository.saveAll(saveProductMaterialVO.toDomain())
    }

    private fun validateSaveCondition(saveProductMaterialVO: SaveProductMaterialVO) {
        findProductOrException(saveProductMaterialVO.productId)

        val productMaterials = saveProductMaterialVO.toDomain()
        val savedProductMaterials = productMaterialRepository.findByProduct(saveProductMaterialVO.productId)

        savedProductMaterials.combineForValidation(productMaterials)
    }

    private fun findProductOrException(productId: Long): Product {
        return productRepository.findById(productId)
    }

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
}