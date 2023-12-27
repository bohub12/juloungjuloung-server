package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.application.dto.UpdateBraceletAdditionalDto
import com.juloungjuloung.juju.application.dto.UpdateNecklaceAdditionalDto
import com.juloungjuloung.juju.application.dto.UpdateProductDto
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest
import com.juloungjuloung.juju.dto.product.request.UpdateBraceletAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.UpdateNecklaceAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.UpdateProductRequest
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

        fun toUpdateDto(request: UpdateProductRequest): UpdateProductDto {
            return UpdateProductDto(
                id = request.id,
                productType = request.productType,
                name = request.name,
                price = request.price,
                weightByMilliGram = request.weightByMilliGram,
                isDiamond = request.isDiamond,
                totalDiamondCaratX100 = request.totalDiamondCaratX100,
                isDisplay = request.isDisplay,
                additionalBraceletRequest = toNestedDto(request.additionalBraceletRequest),
                additionalNecklaceAdditionalRequest = toNestedDto(request.additionalNecklaceAdditionalRequest)
            )
        }

        private fun toNestedDto(request: UpdateBraceletAdditionalRequest?): UpdateBraceletAdditionalDto? {
            request?.let {
                return UpdateBraceletAdditionalDto(
                    maximumLength = request.maximumLength,
                    minimumLength = request.minimumLength
                )
            }

            return null
        }

        private fun toNestedDto(request: UpdateNecklaceAdditionalRequest?): UpdateNecklaceAdditionalDto? {
            request?.let {
                return UpdateNecklaceAdditionalDto(
                    maximumLength = request.maximumLength,
                    minimumLength = request.minimumLength
                )
            }

            return null
        }
    }
}