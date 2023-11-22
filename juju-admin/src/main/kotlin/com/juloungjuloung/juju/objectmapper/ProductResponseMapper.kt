package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.dto.product.response.ProductResponse
import com.juloungjuloung.juju.dto.product.response.impl.BraceletResponse
import com.juloungjuloung.juju.dto.product.response.impl.EarringResponse
import com.juloungjuloung.juju.dto.product.response.impl.NecklaceResponse
import com.juloungjuloung.juju.dto.product.response.impl.RingResponse
import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.BraceletCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.EarringCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.NecklaceCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.RingCommandResult

class ProductResponseMapper {
    companion object {
        fun toResponse(result: ProductCommandResult): ProductResponse {
            return when (result) {
                is BraceletCommandResult -> BraceletResponse.of(result)
                is EarringCommandResult -> EarringResponse.of(result)
                is NecklaceCommandResult -> NecklaceResponse.of(result)
                is RingCommandResult -> RingResponse.of(result)
                else -> ProductResponse.of(result)
            }
        }
    }
}