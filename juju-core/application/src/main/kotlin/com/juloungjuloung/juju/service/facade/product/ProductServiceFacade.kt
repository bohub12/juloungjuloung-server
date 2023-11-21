package com.juloungjuloung.juju.service.facade.product

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.common.utils.findBy
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import com.juloungjuloung.juju.objectmapper.ProductRequestMapper
import com.juloungjuloung.juju.objectmapper.ProductResponseMapper
import com.juloungjuloung.juju.s3.AwsS3Service
import com.juloungjuloung.juju.service.factory.ProductServiceFactory
import org.springframework.stereotype.Service

@Service
class ProductServiceFacade(
    private val productServiceFactory: ProductServiceFactory,
    private val awsS3Service: AwsS3Service
) {
    fun readProducts(productType: String, page: Int, size: Int): List<ProductResponse> {
        val service = productServiceFactory.get(
            (ProductTypeEnum::name findBy productType) ?: throw IllegalStateException()
        )

        return service.read(page, size).stream()
            .map { ProductResponseMapper.toResponse(it) }
            .toList()
    }

    fun saveProducts(saveProductRequest: SaveProductRequest): Boolean {
        val service = productServiceFactory.get(
            (ProductTypeEnum::name findBy saveProductRequest.productType) ?: throw IllegalStateException()
        )

        return service.save(
            ProductRequestMapper.toDomain(
                saveProductRequest,
                service.getProductType()
            )
        )
    }
}