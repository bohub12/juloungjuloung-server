package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.combineForValidation
import com.juloungjuloung.juju.domain.product.repository.ProductMaterialRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductMaterialVO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
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
}