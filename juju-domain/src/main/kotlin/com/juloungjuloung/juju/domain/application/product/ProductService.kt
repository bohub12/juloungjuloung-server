package com.juloungjuloung.juju.domain.application.product

import com.juloungjuloung.juju.domain.domain.product.Product
import com.juloungjuloung.juju.domain.infrastructure.repository.ProductRepository
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService(val productRepository: ProductRepository) {

    fun readProducts(pageable: Pageable): List<Product> {
        return productRepository.findProducts(pageable)
    }
}