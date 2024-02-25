package com.juloungjuloung.juju.domain.productcolor

import com.juloungjuloung.juju.domain.product.vo.UpsertProductColorInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductColorVO
import com.juloungjuloung.juju.enums.ProductColorEnum.GOLD
import com.juloungjuloung.juju.enums.ProductColorEnum.ROSE_GOLD

fun upsertProductColorVOFixture(productId: Long = 1L, duplicatedColor: Boolean = false): UpsertProductColorVO {
    return UpsertProductColorVO(
        productId = productId,
        upsertProductColorInternalVOs = upsertProductColorInternalVOFixture(duplicatedColor = duplicatedColor)
    )
}

fun upsertProductColorInternalVOFixture(duplicatedColor: Boolean = false): List<UpsertProductColorInternalVO> {
    return if (duplicatedColor) {
        listOf(
            UpsertProductColorInternalVO(color = GOLD),
            UpsertProductColorInternalVO(color = GOLD)
        )
    } else {
        listOf(
            UpsertProductColorInternalVO(color = GOLD),
            UpsertProductColorInternalVO(color = ROSE_GOLD)
        )
    }
}