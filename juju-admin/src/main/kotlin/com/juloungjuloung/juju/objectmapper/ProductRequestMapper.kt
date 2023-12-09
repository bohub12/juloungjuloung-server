package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.dto.product.command.SaveBraceletAdditionalCommand
import com.juloungjuloung.juju.dto.product.command.SaveNecklaceAdditionalCommand
import com.juloungjuloung.juju.dto.product.command.SaveProductCommand
import com.juloungjuloung.juju.dto.product.command.UpdateBraceletAdditionalCommand
import com.juloungjuloung.juju.dto.product.command.UpdateNecklaceAdditionalCommand
import com.juloungjuloung.juju.dto.product.command.UpdateProductCommand
import com.juloungjuloung.juju.dto.product.request.SaveBraceletAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.SaveNecklaceAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest
import com.juloungjuloung.juju.dto.product.request.UpdateBraceletAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.UpdateNecklaceAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.UpdateProductRequest

class ProductRequestMapper {
    companion object {
        fun toCommand(request: SaveProductRequest): SaveProductCommand {
            return SaveProductCommand(
                productType = request.productType,
                name = request.name,
                price = request.price,
                weightByMilliGram = request.weightByMilliGram,
                isDiamond = request.isDiamond,
                totalDiamondCaratX100 = request.totalDiamondCaratX100,
                additionalBraceletCommand = request.additionalBraceletRequest?.let { toCommand(it) },
                additionalNecklaceCommand = request.additionalNecklaceRequest?.let { toCommand(it) }
            )
        }

        fun toCommand(request: UpdateProductRequest): UpdateProductCommand {
            return UpdateProductCommand(
                id = request.id,
                productType = request.productType,
                name = request.name,
                price = request.price,
                weightByMilliGram = request.weightByMilliGram,
                isDiamond = request.isDiamond,
                totalDiamondCaratX100 = request.totalDiamondCaratX100,
                isActive = request.isActive,
                additionalBraceletRequest = request.additionalBraceletRequest?.let { toCommand(it) },
                additionalNecklaceAdditionalRequest = request.additionalNecklaceAdditionalRequest?.let { toCommand(it) }
            )
        }

        private fun toCommand(request: SaveBraceletAdditionalRequest): SaveBraceletAdditionalCommand {
            return SaveBraceletAdditionalCommand(
                maximumLength = request.maximumLength,
                minimumLength = request.minimumLength
            )
        }

        private fun toCommand(request: SaveNecklaceAdditionalRequest): SaveNecklaceAdditionalCommand {
            return SaveNecklaceAdditionalCommand(
                maximumLength = request.maximumLength,
                minimumLength = request.minimumLength
            )
        }

        private fun toCommand(request: UpdateBraceletAdditionalRequest): UpdateBraceletAdditionalCommand {
            return UpdateBraceletAdditionalCommand(
                maximumLength = request.maximumLength,
                minimumLength = request.minimumLength
            )
        }

        private fun toCommand(request: UpdateNecklaceAdditionalRequest): UpdateNecklaceAdditionalCommand {
            return UpdateNecklaceAdditionalCommand(
                maximumLength = request.maximumLength,
                minimumLength = request.minimumLength
            )
        }
    }
}