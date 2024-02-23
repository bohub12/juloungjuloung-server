package com.juloungjuloung.juju.domain.productimage

import com.juloungjuloung.juju.domain.product.vo.UpsertProductImageInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductImageVO
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun upsertProductImageVOFixture(
    productId: Long = 1L,
    isMultipleThumbnailImage: Boolean = false,
    exceedMaxSize: Boolean = false,
    includedPersistedRequest: Boolean = true
): UpsertProductImageVO {
    return fixtureMonkey.giveMeBuilder<UpsertProductImageVO>()
        .setExp(UpsertProductImageVO::productId, productId)
        .setExp(
            UpsertProductImageVO::upsertProductImageInternalVOs,
            upsertProductImageInternalVOFixture(isMultipleThumbnailImage, exceedMaxSize, includedPersistedRequest)
        )
        .sample()
}

private fun upsertProductImageInternalVOFixture(
    isMultipleThumbnailImage: Boolean = false,
    exceedMaxSize: Boolean = false,
    includedPersistedRequest: Boolean = true
): List<UpsertProductImageInternalVO> {
    val dummyThumbnailImageVO = if (includedPersistedRequest) {
        UpsertProductImageInternalVO(1L, "thumbnail.png", true)
    } else {
        UpsertProductImageInternalVO(0L, "thumbnail.png", true)
    }
    val dummyNonThumbnailImageVO = UpsertProductImageInternalVO(0L, "normal.png", false)

    return if (!isMultipleThumbnailImage && !exceedMaxSize) {
        listOf(dummyThumbnailImageVO, dummyNonThumbnailImageVO)
    } else if (isMultipleThumbnailImage) {
        listOf(dummyThumbnailImageVO, dummyThumbnailImageVO)
    } else {
        listOf(
            dummyThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO,
            dummyNonThumbnailImageVO
        )
    }
}