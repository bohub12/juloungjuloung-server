package com.juloungjuloung.juju.application.product

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.infrastructure.repository.ProductRepository
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService(val productRepository: ProductRepository) {

    fun readProducts(pageable: Pageable): List<Product> {
        return emptyList()
    }
}