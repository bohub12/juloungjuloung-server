package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest
import com.juloungjuloung.juju.enums.ProductTypeEnum

class ProductRequestMapper {
    companion object {
        fun toDomain(request: SaveProductRequest): Product {
            return when (request.productType) {
                ProductTypeEnum.BRACELET -> request.toBracelet()
                ProductTypeEnum.EARRING -> request.toEarring()
                ProductTypeEnum.NECKLACE -> request.toNecklace()
                ProductTypeEnum.RING -> request.toRing()
            }
        }
    }
}