package com.juloungjuloung.juju.service.facade.product

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.common.utils.findBy
import com.juloungjuloung.juju.dto.product.command.SaveProductCommand
import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import com.juloungjuloung.juju.objectmapper.ProductCommandMapper
import com.juloungjuloung.juju.objectmapper.ProductCommandResultMapper
import com.juloungjuloung.juju.s3.AwsS3Service
import com.juloungjuloung.juju.service.factory.ProductServiceFactory
import org.springframework.stereotype.Service

@Service
class ProductServiceFacade(
    private val productServiceFactory: ProductServiceFactory,
    private val awsS3Service: AwsS3Service
) {
    fun readProducts(productType: String, page: Int, size: Int): List<ProductCommandResult> {
        val service = productServiceFactory.get(
            (ProductTypeEnum::name findBy productType) ?: throw IllegalStateException()
        )

        return service.read(page, size).stream()
            .map { ProductCommandResultMapper.toCommandResult(it) }
            .toList()
    }

    fun saveProducts(saveProductCommand: SaveProductCommand): Boolean {
        val service = productServiceFactory.get(
            (ProductTypeEnum::name findBy saveProductCommand.productType) ?: throw IllegalStateException()
        )

        return service.save(
            ProductCommandMapper.toDomain(
                saveProductCommand,
                service.getProductType()
            )
        )
    }
}