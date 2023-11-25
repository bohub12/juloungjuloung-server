package com.juloungjuloung.juju.service.factory

import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.service.product.ProductService
import org.springframework.stereotype.Component

@Component
class ProductServiceFactory(productServices: List<ProductService>) {
    val serviceMap = productServices.associateBy { it.getProductType() }

    fun get(productTypeEnum: ProductTypeEnum): ProductService {
        return serviceMap[productTypeEnum] ?: throw IllegalStateException()
    }
}