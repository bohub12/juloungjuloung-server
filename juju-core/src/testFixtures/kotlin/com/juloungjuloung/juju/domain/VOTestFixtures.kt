package com.juloungjuloung.juju.domain

import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import com.navercorp.fixturemonkey.kotlin.setNotNullExp

fun saveRingVO(): SaveProductVO {
    return fixtureMonkey.giveMeBuilder<SaveProductVO>()
        .setExp(SaveProductVO::productType, ProductTypeEnum.RING)
        .setNotNullExp(SaveProductVO::name)
        .setExp(SaveProductVO::price, 10000L)
        .setExp(SaveProductVO::weightByMilliGram, 10000L)
        .setExp(SaveProductVO::isDisplay, true)
        .setNotNullExp(SaveProductVO::thumbnailImage)
        .setExp(SaveProductVO::isDiamond, false)
        .sample()
}