package com.juloungjuloung.juju.domain.productoption

import com.juloungjuloung.juju.domain.product.ProductOption
import com.juloungjuloung.juju.domain.product.ProductOptionCategory
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productOptionCategoryFixture(id: Long = 1L, productId: Long = 1L): ProductOptionCategory {
    return fixtureMonkey.giveMeBuilder<ProductOptionCategory>()
        .setExp(ProductOptionCategory::id, id)
        .setExp(ProductOptionCategory::productId, productId)
        .sample()
}

fun productOptionCollectionFixture(ids: List<Long> = listOf(1L, 2L), optionCategoryId: Long = 1L): List<ProductOption> {
    return List(ids.size) {
        fixtureMonkey.giveMeBuilder<ProductOption>()
            .setExp(ProductOption::id, ids[it])
            .setExp(ProductOption::productOptionCategoryId, optionCategoryId)
            .sample()
    }
}