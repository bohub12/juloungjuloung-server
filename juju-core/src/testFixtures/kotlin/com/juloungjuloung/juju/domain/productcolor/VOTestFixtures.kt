package com.juloungjuloung.juju.domain.productcolor

import com.juloungjuloung.juju.domain.product.vo.SaveProductColorInternalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductColorVO
import com.juloungjuloung.juju.enums.ProductColorEnum.GOLD
import com.juloungjuloung.juju.enums.ProductColorEnum.ROSE_GOLD

fun saveProductColorVOFixture(productId: Long = 1L, duplicatedColor: Boolean = false): SaveProductColorVO {
    return SaveProductColorVO(
        productId = productId,
        saveProductColorInternalVOs = saveProductColorInternalVOFixtures(duplicatedColor = duplicatedColor)
    )
}

fun saveProductColorInternalVOFixtures(duplicatedColor: Boolean = false): List<SaveProductColorInternalVO> {
    return if (duplicatedColor) {
        listOf(
            SaveProductColorInternalVO(color = GOLD),
            SaveProductColorInternalVO(color = GOLD)
        )
    } else {
        listOf(
            SaveProductColorInternalVO(color = GOLD),
            SaveProductColorInternalVO(color = ROSE_GOLD)
        )
    }
}