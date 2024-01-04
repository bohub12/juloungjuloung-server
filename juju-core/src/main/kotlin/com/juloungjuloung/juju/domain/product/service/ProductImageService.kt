package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductImageService(
    private val productImageRepository: ProductImageRepository
) {

    fun createVirtualPath(): String {
        return UUID.randomUUID().toString()
    }
}