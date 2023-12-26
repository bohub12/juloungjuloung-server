package com.juloungjuloung.juju.service.facade.product

import com.juloungjuloung.juju.application.factory.ProductServiceFactory
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.dto.product.command.UpdateProductCommand
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.objectmapper.ProductCommandMapper
import org.springframework.stereotype.Service

@Service
class ProductServiceFacade(
    private val productServiceFactory: ProductServiceFactory
) {
    fun readProducts(productType: ProductTypeEnum, page: Int, size: Int): List<Product> {
        val service = productServiceFactory.get(productType)

        return service.read(page, size)
    }

    fun saveProducts(product: Product): Long {
        val service = productServiceFactory.get(command.productType)

        return service.save(
            ProductCommandMapper.toDomain(command)
        )
    }

    fun updateProducts(command: UpdateProductCommand): Long {
        val service = productServiceFactory.get(command.productType)

        val product = service.readById(command.id)

        product.update(
            name = command.name,
            price = command.price,
            weightByMilliGram = command.weightByMilliGram,
            isDiamond = command.isDiamond,
            totalDiamondCaratX100 = command.totalDiamondCaratX100,
            isActive = command.isActive,
            braceletMaximumLength = command.additionalBraceletRequest?.maximumLength,
            braceletMinimumLength = command.additionalBraceletRequest?.minimumLength,
            necklaceMaximumLength = command.additionalNecklaceAdditionalRequest?.maximumLength,
            necklaceMinimumLength = command.additionalNecklaceAdditionalRequest?.minimumLength
        )

        return service.update(product)
    }
}