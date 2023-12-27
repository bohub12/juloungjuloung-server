package com.juloungjuloung.juju.service.facade.product

import com.juloungjuloung.juju.application.dto.UpdateProductDto
import com.juloungjuloung.juju.application.factory.ProductServiceFactory
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.enums.ProductTypeEnum
import org.springframework.stereotype.Service

@Service
class ProductServiceFacade(
    private val productServiceFactory: ProductServiceFactory
) {
    fun readProducts(productType: ProductTypeEnum, page: Int, size: Int): List<Product> {
        val service = productServiceFactory.get(productType)

        return service.read(page, size)
    }

    fun saveProduct(product: Product): Long {
        val service = productServiceFactory.get(product.type)

        return service.save(product)
    }

    fun updateProduct(updateProductDto: UpdateProductDto): Long {
        val service = productServiceFactory.get(updateProductDto.productType)

        val findProduct = service.readById(updateProductDto.id)
        findProduct.update(updateProductDto)

        return service.update(findProduct)
    }
}