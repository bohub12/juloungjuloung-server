package com.juloungjuloung.juju.service.facade.product

import com.juloungjuloung.juju.dto.product.command.SaveProductCommand
import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.objectmapper.ProductCommandMapper
import com.juloungjuloung.juju.objectmapper.ProductCommandResultMapper
import com.juloungjuloung.juju.service.factory.ProductServiceFactory
import org.springframework.stereotype.Service

@Service
class ProductServiceFacade(
    private val productServiceFactory: ProductServiceFactory
) {
    fun readProducts(productType: ProductTypeEnum, page: Int, size: Int): List<ProductCommandResult> {
        val service = productServiceFactory.get(productType)

        return service.read(page, size).stream()
            .map { ProductCommandResultMapper.toCommandResult(it) }
            .toList()
    }

    fun saveProducts(saveProductCommand: SaveProductCommand): Boolean {
        val service = productServiceFactory.get(saveProductCommand.productType)

        return service.save(
            ProductCommandMapper.toDomain(saveProductCommand)
        )
    }
}