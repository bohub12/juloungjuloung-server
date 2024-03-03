package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductOptionCategory
import com.juloungjuloung.juju.domain.product.repository.ProductOptionCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductOptionCategoryService(
    private val productOptionCategoryRepository: ProductOptionCategoryRepository
) {

    @Transactional(readOnly = true)
    fun readById(productOptionCategoryId: Long): ProductOptionCategory {
        return productOptionCategoryRepository.findById(productOptionCategoryId)
    }

    @Transactional
    fun upsert(productOptionCategory: ProductOptionCategory): Long {
        if (productOptionCategory.isPersisted()) {
            return productOptionCategoryRepository.update(productOptionCategory)
        }

        return productOptionCategoryRepository.save(productOptionCategory)
    }
}