package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum

interface ProductService {

    fun read(page: Int, size: Int): List<Product>

    fun readById(id: Long): Product

    fun save(saveProductVO: SaveProductVO): Long

    fun update(updateProductVO: UpdateProductVO): Long

    fun getProductType(): ProductTypeEnum
}