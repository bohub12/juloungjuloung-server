package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest

class ProductRequestMapper {
    companion object {
        fun toDomain(saveRequest: SaveProductRequest, productTypeEnum: ProductTypeEnum): Product {
            when (productTypeEnum) {
                ProductTypeEnum.BRACELET -> {
                    return Bracelet(
                        name = saveRequest.name,
                        productCode = "dd", // TODO : productCode
                        price = saveRequest.price,
                        weightByMilliGram = saveRequest.weightByMilliGram,
                        isDiamond = saveRequest.isDiamond,
                        totalDiamondCaratX100 = saveRequest.totalDiamondCaratX100,
                        maximumLength = saveRequest.additionalBraceletRequest!!.maximumLength,
                        minimumLength = saveRequest.additionalBraceletRequest.minimumLength
                    )
                }
                ProductTypeEnum.EARRING -> {
                    return Earring(
                        name = saveRequest.name,
                        productCode = "dd", // TODO : productCode
                        price = saveRequest.price,
                        weightByMilliGram = saveRequest.weightByMilliGram,
                        isDiamond = saveRequest.isDiamond,
                        totalDiamondCaratX100 = saveRequest.totalDiamondCaratX100
                    )
                }
                ProductTypeEnum.NECKLACE -> {
                    return Necklace(
                        name = saveRequest.name,
                        productCode = "dd", // TODO : productCode
                        price = saveRequest.price,
                        weightByMilliGram = saveRequest.weightByMilliGram,
                        isDiamond = saveRequest.isDiamond,
                        totalDiamondCaratX100 = saveRequest.totalDiamondCaratX100,
                        maximumLength = saveRequest.additionalNecklaceRequest!!.maximumLength,
                        minimumLength = saveRequest.additionalNecklaceRequest.minimumLength
                    )
                }
                ProductTypeEnum.RING -> {
                    return Ring(
                        name = saveRequest.name,
                        productCode = "dd", // TODO : productCode
                        price = saveRequest.price,
                        weightByMilliGram = saveRequest.weightByMilliGram,
                        isDiamond = saveRequest.isDiamond,
                        totalDiamondCaratX100 = saveRequest.totalDiamondCaratX100
                    )
                }
            }
        }
    }
}