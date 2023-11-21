package com.juloungjuloung.juju.service.product

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.Product

interface ProductService {

    fun read(page: Int, size: Int): List<Product>

    fun save(product: Product): Boolean

    fun getProductType(): ProductTypeEnum
}