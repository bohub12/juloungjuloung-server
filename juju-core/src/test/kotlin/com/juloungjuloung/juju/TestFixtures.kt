package com.juloungjuloung.juju

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import com.navercorp.fixturemonkey.kotlin.setNotNull

val fixtureMonkey: FixtureMonkey = FixtureMonkey.builder()
    .plugin(KotlinPlugin())
    .build()

fun braceletFixture(): Bracelet {
    return fixtureMonkey.giveMeBuilder<Bracelet>()
        .setExp(Bracelet::type, ProductTypeEnum.BRACELET)
        .acceptIf(
            { it.isDisplay },
            { builder -> builder.setNotNull(Bracelet::thumbnailImage) }
        )
        .sample()
}

fun earringFixture(): Earring {
    return fixtureMonkey.giveMeBuilder<Earring>()
        .setExp(Earring::type, ProductTypeEnum.EARRING)
        .acceptIf(
            { it.isDisplay },
            { builder -> builder.setNotNull(Earring::thumbnailImage) }
        )
        .sample()
}

fun necklaceFixture(): Necklace {
    return fixtureMonkey.giveMeBuilder<Necklace>()
        .setExp(Necklace::type, ProductTypeEnum.NECKLACE)
        .acceptIf(
            { it.isDisplay },
            { builder -> builder.setNotNull(Necklace::thumbnailImage) }
        )
        .sample()
}

fun ringFixture(): Ring {
    return fixtureMonkey.giveMeBuilder<Ring>()
        .setExp(Ring::type, ProductTypeEnum.RING)
        .acceptIf(
            { it.isDisplay },
            { builder -> builder.setNotNull(Ring::thumbnailImage) }
        )
        .sample()
}