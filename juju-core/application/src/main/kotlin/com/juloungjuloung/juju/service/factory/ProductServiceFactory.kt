package com.juloungjuloung.juju.service.factory

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.service.product.ProductService
import org.springframework.stereotype.Component

@Component
class ProductServiceFactory(productServices: List<ProductService>) {
    val serviceMap = HashMap<ProductTypeEnum, ProductService>()

    init {
        productServices.forEach { serviceMap[it.getProductType()] = it }
    }

    fun get(productTypeEnum: ProductTypeEnum): ProductService {
        return serviceMap[productTypeEnum] ?: throw IllegalStateException()
    }
}