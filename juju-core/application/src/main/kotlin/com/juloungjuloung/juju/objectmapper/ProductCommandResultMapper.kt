package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.dto.product.result.ProductCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.BraceletCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.EarringCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.NecklaceCommandResult
import com.juloungjuloung.juju.dto.product.result.impl.RingCommandResult

class ProductCommandResultMapper {
    companion object {
        fun toCommandResult(product: Product): ProductCommandResult {
            return when (product) {
                is Bracelet -> BraceletCommandResult.of(product)
                is Earring -> EarringCommandResult.of(product)
                is Necklace -> NecklaceCommandResult.of(product)
                is Ring -> RingCommandResult.of(product)
                else -> ProductCommandResult.of(product)
            }
        }
    }
}