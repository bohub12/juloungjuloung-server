package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import com.navercorp.fixturemonkey.kotlin.setNotNullExp

fun braceletFixture(updatable: Boolean = false, id: Long = 0L, isDisplay: Boolean = false): Bracelet {
    return fixtureMonkey.giveMeBuilder<Bracelet>()
        .setExp(Bracelet::productType, ProductTypeEnum.BRACELET)
        .setExp(Bracelet::id, 0L)
        .setExp(Bracelet::price, 10000L)
        .setExp(Bracelet::weightByMilliGram, 10000L)
        .setExp(Bracelet::isDisplay, isDisplay)
        .setExp(Bracelet::minimumLength, 10)
        .setExp(Bracelet::maximumLength, 100)
        .acceptIf(
            { isDisplay },
            { builder -> builder.setNotNullExp(Bracelet::thumbnailImage) }
        )
        .acceptIf(
            { updatable },
            { builder -> builder.setExp(Bracelet::id, id) }
        )
        .sample()
}

fun earringFixture(updatable: Boolean = false, id: Long = 0L, isDisplay: Boolean = false): Earring {
    return fixtureMonkey.giveMeBuilder<Earring>()
        .setExp(Earring::productType, ProductTypeEnum.EARRING)
        .setExp(Earring::id, 0L)
        .setExp(Earring::price, 10000L)
        .setExp(Earring::weightByMilliGram, 10000L)
        .setExp(Earring::isDisplay, isDisplay)
        .acceptIf(
            { isDisplay },
            { builder -> builder.setNotNullExp(Earring::thumbnailImage) }
        )
        .acceptIf(
            { updatable },
            { builder -> builder.setExp(Earring::id, id) }
        )
        .sample()
}

fun necklaceFixture(updatable: Boolean = false, id: Long = 0L, isDisplay: Boolean = false): Necklace {
    return fixtureMonkey.giveMeBuilder<Necklace>()
        .setExp(Necklace::productType, ProductTypeEnum.NECKLACE)
        .setExp(Necklace::id, 0L)
        .setExp(Necklace::price, 10000L)
        .setExp(Necklace::weightByMilliGram, 10000L)
        .setExp(Necklace::isDisplay, isDisplay)
        .setExp(Necklace::minimumLength, 10)
        .setExp(Necklace::maximumLength, 100)
        .acceptIf(
            { isDisplay },
            { builder -> builder.setNotNullExp(Necklace::thumbnailImage) }
        )
        .acceptIf(
            { updatable },
            { builder -> builder.setExp(Necklace::id, id) }
        )
        .sample()
}

fun ringFixture(updatable: Boolean = false, id: Long = 0L, isDisplay: Boolean = false): Ring {
    return fixtureMonkey.giveMeBuilder<Ring>()
        .setExp(Ring::productType, ProductTypeEnum.RING)
        .setExp(Ring::id, 0L)
        .setExp(Ring::price, 10000L)
        .setExp(Ring::weightByMilliGram, 10000L)
        .setExp(Ring::isDisplay, isDisplay)
        .acceptIf(
            { isDisplay },
            { builder -> builder.setNotNullExp(Ring::thumbnailImage) }
        )
        .acceptIf(
            { updatable },
            { builder -> builder.setExp(Ring::id, id) }
        )
        .sample()
}