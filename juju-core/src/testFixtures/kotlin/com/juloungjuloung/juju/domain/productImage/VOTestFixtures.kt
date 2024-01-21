package com.juloungjuloung.juju.domain.productImage

import com.juloungjuloung.juju.domain.product.vo.SaveProductImageInternalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun saveProductImageVOFixture(
    productId: Long = 1L,
    isMultiplePrimaryImage: Boolean = false,
    exceedMaxSize: Boolean = false
): SaveProductImageVO {
    return fixtureMonkey.giveMeBuilder<SaveProductImageVO>()
        .setExp(SaveProductImageVO::productId, productId)
        .setExp(
            SaveProductImageVO::saveProductImageInternalVOs,
            saveProductImageInternalVOFixture(isMultiplePrimaryImage, exceedMaxSize)
        )
        .sample()
}

private fun saveProductImageInternalVOFixture(
    isMultiplePrimaryImage: Boolean = false,
    exceedMaxSize: Boolean = false
): List<SaveProductImageInternalVO> {
    val dummyPrimaryImageVO = SaveProductImageInternalVO("primary.png", true)
    val dummyNormalImageVO = SaveProductImageInternalVO("normal.png", false)

    return if (!isMultiplePrimaryImage && !exceedMaxSize) {
        listOf(dummyPrimaryImageVO, dummyNormalImageVO)
    } else if (isMultiplePrimaryImage) {
        listOf(dummyPrimaryImageVO, dummyPrimaryImageVO)
    } else {
        listOf(
            dummyPrimaryImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO,
            dummyNormalImageVO
        )
    }
}