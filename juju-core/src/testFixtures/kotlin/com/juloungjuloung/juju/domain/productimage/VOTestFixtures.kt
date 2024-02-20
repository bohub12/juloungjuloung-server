package com.juloungjuloung.juju.domain.productimage

import com.juloungjuloung.juju.domain.product.vo.SaveProductImageInternalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun saveProductImageVOFixture(
    productId: Long = 1L,
    isMultipleThumbnailImage: Boolean = false,
    exceedMaxSize: Boolean = false
): SaveProductImageVO {
    return fixtureMonkey.giveMeBuilder<SaveProductImageVO>()
        .setExp(SaveProductImageVO::productId, productId)
        .setExp(
            SaveProductImageVO::saveProductImageInternalVOs,
            saveProductImageInternalVOFixture(isMultipleThumbnailImage, exceedMaxSize)
        )
        .sample()
}

private fun saveProductImageInternalVOFixture(
    isMultipleThumbnailImage: Boolean = false,
    exceedMaxSize: Boolean = false
): List<SaveProductImageInternalVO> {
    val dummyPrimaryImageVO = SaveProductImageInternalVO("thumbnail.png", true)
    val dummyNormalImageVO = SaveProductImageInternalVO("normal.png", false)

    return if (!isMultipleThumbnailImage && !exceedMaxSize) {
        listOf(dummyPrimaryImageVO, dummyNormalImageVO)
    } else if (isMultipleThumbnailImage) {
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