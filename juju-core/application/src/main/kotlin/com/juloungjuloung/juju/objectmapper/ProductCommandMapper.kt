package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.dto.product.command.SaveProductCommand

class ProductCommandMapper {
    companion object {
        fun toDomain(command: SaveProductCommand, productTypeEnum: ProductTypeEnum): Product {
            when (productTypeEnum) {
                ProductTypeEnum.BRACELET -> {
                    return Bracelet(
                        name = command.name,
                        productCode = "dd", // TODO : productCode
                        price = command.price,
                        weightByMilliGram = command.weightByMilliGram,
                        isDiamond = command.isDiamond,
                        totalDiamondCaratX100 = command.totalDiamondCaratX100,
                        maximumLength = command.additionalBraceletCommand!!.maximumLength,
                        minimumLength = command.additionalBraceletCommand.minimumLength
                    )
                }
                ProductTypeEnum.EARRING -> {
                    return Earring(
                        name = command.name,
                        productCode = "dd", // TODO : productCode
                        price = command.price,
                        weightByMilliGram = command.weightByMilliGram,
                        isDiamond = command.isDiamond,
                        totalDiamondCaratX100 = command.totalDiamondCaratX100
                    )
                }
                ProductTypeEnum.NECKLACE -> {
                    return Necklace(
                        name = command.name,
                        productCode = "dd", // TODO : productCode
                        price = command.price,
                        weightByMilliGram = command.weightByMilliGram,
                        isDiamond = command.isDiamond,
                        totalDiamondCaratX100 = command.totalDiamondCaratX100,
                        maximumLength = command.additionalNecklaceCommand!!.maximumLength,
                        minimumLength = command.additionalNecklaceCommand.minimumLength
                    )
                }
                ProductTypeEnum.RING -> {
                    return Ring(
                        name = command.name,
                        productCode = "dd", // TODO : productCode
                        price = command.price,
                        weightByMilliGram = command.weightByMilliGram,
                        isDiamond = command.isDiamond,
                        totalDiamondCaratX100 = command.totalDiamondCaratX100
                    )
                }
            }
        }
    }
}