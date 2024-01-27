package com.juloungjuloung.juju.domain.productmaterial

import com.juloungjuloung.juju.domain.product.vo.SaveProductMaterialInternalVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductMaterialVO
import com.juloungjuloung.juju.enums.ProductMaterialEnum
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K18
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K22
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun saveProductMaterialVOFixture(
    productId: Long = 1L,
    materials: List<ProductMaterialEnum> = listOf(K18, K22)
): SaveProductMaterialVO {
    return fixtureMonkey.giveMeBuilder<SaveProductMaterialVO>()
        .setExp(SaveProductMaterialVO::productId, productId)
        .setExp(SaveProductMaterialVO::saveProductMaterialInternalVOs, saveProductMaterialInternalVOFixture(materials))
        .sample()
}

private fun saveProductMaterialInternalVOFixture(
    materials: List<ProductMaterialEnum> = listOf(
        K18,
        K22
    )
): List<SaveProductMaterialInternalVO> {
    return materials.map {
        fixtureMonkey.giveMeBuilder<SaveProductMaterialInternalVO>()
            .setExp(SaveProductMaterialInternalVO::material, it)
            .sample()
    }
}