package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.application.factory.ProductServiceFactory
import com.juloungjuloung.juju.domain.product.ProductsWithCount
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductServiceFacade(
    private val productServiceFactory: ProductServiceFactory
) {

    fun read(productType: ProductTypeEnum, page: Int, size: Int): ProductsWithCount {
        val service = productServiceFactory.get(productType)

        return ProductsWithCount(
            service.read(page, size),
            totalElementCount = service.count()
        )
    }

    @Transactional
    fun save(saveProductVO: SaveProductVO): Long {
        return productServiceFactory.get(saveProductVO.productType).save(saveProductVO)
    }

    @Transactional
    fun update(updateProductVO: UpdateProductVO): Long {
        return productServiceFactory.get(updateProductVO.productType).update(updateProductVO)
    }
}