package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productFixture(updatable: Boolean = false, id: Long = 0L, isDisplay: Boolean = false): Product {
    return fixtureMonkey.giveMeBuilder<Product>()
        .setExp(Product::productType, ProductTypeEnum.BASE)
        .setExp(Product::id, 0L)
        .setExp(Product::price, 10000L)
        .setExp(Product::weightByMilliGram, 10000L)
        .setExp(Product::isDisplay, isDisplay)
        .setExp(Product::thumbnailImage, "https://test.com/test.png")
        .acceptIf(
            { updatable },
            { builder -> builder.setExp(Product::id, id) }
        )
        .sample()
}

fun braceletFixture(updatable: Boolean = false, id: Long = 0L, isDisplay: Boolean = false): Bracelet {
    return fixtureMonkey.giveMeBuilder<Bracelet>()
        .setExp(Bracelet::productType, ProductTypeEnum.BRACELET)
        .setExp(Bracelet::id, 0L)
        .setExp(Bracelet::price, 10000L)
        .setExp(Bracelet::weightByMilliGram, 10000L)
        .setExp(Bracelet::isDisplay, isDisplay)
        .setExp(Bracelet::thumbnailImage, "https://test.com/test.png")
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
        .setExp(Earring::thumbnailImage, "https://test.com/test.png")
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
        .setExp(Necklace::thumbnailImage, "https://test.com/test.png")
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
        .setExp(Ring::thumbnailImage, "https://test.com/test.png")
        .acceptIf(
            { updatable },
            { builder -> builder.setExp(Ring::id, id) }
        )
        .sample()
}