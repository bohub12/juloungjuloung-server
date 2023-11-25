package com.juloungjuloung.juju.service.product

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.enums.ProductTypeEnum

interface ProductService {

    fun read(page: Int, size: Int): List<Product>

    fun readById(id: Long): Product

    fun save(product: Product): Boolean

    fun update(product: Product): Boolean

    fun getProductType(): ProductTypeEnum
}