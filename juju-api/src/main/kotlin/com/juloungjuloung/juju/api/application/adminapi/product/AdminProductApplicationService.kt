package com.juloungjuloung.juju.api.application.adminapi.product

import com.juloungjuloung.juju.domain.application.product.ProductService
import com.juloungjuloung.juju.domain.domain.product.Product
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable
import org.springframework.stereotype.Service

@Service
class AdminProductApplicationService(
    val productService: ProductService
) {
    fun readProductsByAdmin(pageable: Pageable): List<Product> {
        return emptyList()
    }
}