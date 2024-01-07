package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.ProductImages
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class ProductImageService(
    private val productImageRepository: ProductImageRepository
) {

    fun createVirtualPath(): String {
        return UUID.randomUUID().toString()
    }

    @Transactional
    fun saveAll(productImages: ProductImages): List<Long> {
        return productImageRepository.saveAll(productImages)
    }
}