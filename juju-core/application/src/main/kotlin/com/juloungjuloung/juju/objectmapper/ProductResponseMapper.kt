package com.juloungjuloung.juju.objectmapper

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.dto.product.response.ProductResponse
import com.juloungjuloung.juju.dto.product.response.impl.BraceletResponse
import com.juloungjuloung.juju.dto.product.response.impl.EarringResponse
import com.juloungjuloung.juju.dto.product.response.impl.NecklaceResponse
import com.juloungjuloung.juju.dto.product.response.impl.RingResponse

class ProductResponseMapper {
    companion object {
        fun toResponse(product: Product): ProductResponse {
            return when (product) {
                is Bracelet -> BraceletResponse.of(product)
                is Earring -> EarringResponse.of(product)
                is Necklace -> NecklaceResponse.of(product)
                is Ring -> RingResponse.of(product)
                else -> ProductResponse.of(product)
            }
        }
    }
}