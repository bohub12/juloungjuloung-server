package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductOption
import com.juloungjuloung.juju.domain.product.ProductOptions
import com.juloungjuloung.juju.domain.product.repository.ProductOptionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductOptionService(
    private val productOptionRepository: ProductOptionRepository
) {

    @Transactional(readOnly = true)
    fun readByIds(productOptionIds: List<Long>): List<ProductOption> {
        return productOptionRepository.findByIds(productOptionIds)
    }

    @Transactional
    fun upsert(productOptions: ProductOptions): List<Long> {
        return productOptionRepository.updateAll(productOptions.filterPersisted())
            .plus(productOptionRepository.saveAll(productOptions.filterNotPersisted()))
    }
}