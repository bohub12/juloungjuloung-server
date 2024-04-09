package com.juloungjuloung.juju.domain.productoption

import com.juloungjuloung.juju.domain.product.vo.UpsertProductOptionCategoryInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductOptionInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductOptionVO
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun upsertProductOptionVOFixture(
    productId: Long = 1L,
    optionCategoryId: Long = 1L,
    optionIds: List<Long> =
        listOf(
            1L,
            2L
        ),
    isEmptyOption: Boolean = false
): UpsertProductOptionVO {
    if (isEmptyOption) {
        return fixtureMonkey.giveMeBuilder<UpsertProductOptionVO>()
            .setExp(UpsertProductOptionVO::productId, productId)
            .setExp(UpsertProductOptionVO::optionCategory, upsertOptionCategoryVOFixture(optionCategoryId))
            .setExp(UpsertProductOptionVO::options, listOf<UpsertProductOptionVO>())
            .sample()
    }

    return fixtureMonkey.giveMeBuilder<UpsertProductOptionVO>()
        .setExp(UpsertProductOptionVO::productId, productId)
        .setExp(UpsertProductOptionVO::optionCategory, upsertOptionCategoryVOFixture(optionCategoryId))
        .setExp(UpsertProductOptionVO::options, upsertOptionVOFixture(optionIds = optionIds))
        .sample()
}

private fun upsertOptionCategoryVOFixture(optionCategoryId: Long): UpsertProductOptionCategoryInternalVO {
    return fixtureMonkey.giveMeBuilder<UpsertProductOptionCategoryInternalVO>()
        .setExp(UpsertProductOptionCategoryInternalVO::id, optionCategoryId)
        .sample()
}

private fun upsertOptionVOFixture(optionIds: List<Long> = listOf(1L, 2L)): List<UpsertProductOptionInternalVO> {
    return List(optionIds.size) {
        fixtureMonkey.giveMeBuilder<UpsertProductOptionInternalVO>()
            .setExp(UpsertProductOptionInternalVO::id, optionIds[it])
            .sample()
    }
}