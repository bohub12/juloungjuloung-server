package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.application.factory.ProductServiceFactory
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
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

    fun saveProduct(saveProductVO: SaveProductVO): Long {
        val service = productServiceFactory.get(saveProductVO.productType)

        return service.save(saveProductVO.toDomain())
    }

    fun updateProduct(updateProductVO: UpdateProductVO): Long {
        val service = productServiceFactory.get(updateProductVO.productType)

        val findProduct = service.readById(updateProductVO.id)
        findProduct.update(updateProductVO)

        return service.update(findProduct)
    }
}