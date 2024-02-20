package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.vo.SaveBraceletAdditionalVO
import com.juloungjuloung.juju.domain.product.vo.SaveNecklaceAdditionalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateBraceletAdditionalVO
import com.juloungjuloung.juju.domain.product.vo.UpdateNecklaceAdditionalVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.dto.product.request.SaveBraceletAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.SaveNecklaceAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.SaveProductRequest
import com.juloungjuloung.juju.dto.product.request.UpdateBraceletAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.UpdateNecklaceAdditionalRequest
import com.juloungjuloung.juju.dto.product.request.UpdateProductRequest

class ProductRequestMapper {
    companion object {
        fun toSaveVO(request: SaveProductRequest): SaveProductVO {
            return SaveProductVO(
                productType = request.productType,
                name = request.name,
                price = request.price,
                weightByMilliGram = request.weightByMilliGram,
                isDiamond = request.isDiamond,
                totalDiamondCaratX100 = request.totalDiamondCaratX100,
                isDisplay = request.isDisplay,
                saveBraceletAdditionalVO = request.saveBraceletAdditionalRequest?.let {
                    toSaveBraceletAdditionalVO(it)
                },
                saveNecklaceAdditionalVO = request.saveNecklaceAdditionalRequest?.let {
                    toSaveNecklaceAdditionalVO(it)
                }
            )
        }

        private fun toSaveBraceletAdditionalVO(request: SaveBraceletAdditionalRequest): SaveBraceletAdditionalVO {
            return SaveBraceletAdditionalVO(
                maximumLength = request.maximumLength,
                minimumLength = request.minimumLength
            )
        }

        private fun toSaveNecklaceAdditionalVO(request: SaveNecklaceAdditionalRequest): SaveNecklaceAdditionalVO {
            return SaveNecklaceAdditionalVO(
                maximumLength = request.maximumLength,
                minimumLength = request.minimumLength
            )
        }

        fun toUpdateVO(request: UpdateProductRequest): UpdateProductVO {
            return UpdateProductVO(
                id = request.id,
                productType = request.productType,
                name = request.name,
                price = request.price,
                weightByMilliGram = request.weightByMilliGram,
                isDiamond = request.isDiamond,
                totalDiamondCaratX100 = request.totalDiamondCaratX100,
                isDisplay = request.isDisplay,
                additionalBraceletVO = toUpdateBraceletVO(request.updateBraceletAdditionalRequest),
                additionalNecklaceVO = toUpdateNecklaceVO(request.updateNecklaceAdditionalRequest)
            )
        }

        private fun toUpdateBraceletVO(request: UpdateBraceletAdditionalRequest?): UpdateBraceletAdditionalVO? {
            request?.let {
                return UpdateBraceletAdditionalVO(
                    maximumLength = request.maximumLength,
                    minimumLength = request.minimumLength
                )
            }

            return null
        }

        private fun toUpdateNecklaceVO(request: UpdateNecklaceAdditionalRequest?): UpdateNecklaceAdditionalVO? {
            request?.let {
                return UpdateNecklaceAdditionalVO(
                    maximumLength = request.maximumLength,
                    minimumLength = request.minimumLength
                )
            }

            return null
        }
    }
}