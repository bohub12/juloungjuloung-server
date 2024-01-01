package com.juloungjuloung.juju.application.factory

import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.enums.ProductTypeEnum
import org.springframework.stereotype.Component

@Component
class ProductServiceFactory(productServices: List<ProductService>) {
    val serviceMap: Map<ProductTypeEnum, ProductService> = productServices.associateBy { it.getProductType() }

    fun get(productTypeEnum: ProductTypeEnum): ProductService {
        return serviceMap[productTypeEnum] ?: throw IllegalStateException()
    }
}