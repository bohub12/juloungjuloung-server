package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.enums.ProductTypeEnum

interface ProductService {

    fun read(page: Int, size: Int): List<Product>

    fun readById(id: Long): Product

    fun save(product: Product): Long

    fun update(product: Product): Long

    fun getProductType(): ProductTypeEnum
}