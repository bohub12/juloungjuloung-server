package com.juloungjuloung.juju.domain.productmaterial

import com.juloungjuloung.juju.domain.product.vo.UpsertProductMaterialInternalVO
import com.juloungjuloung.juju.domain.product.vo.UpsertProductMaterialVO
import com.juloungjuloung.juju.enums.ProductMaterialEnum
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K14
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K18
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun upsertProductMaterialVOFixture(
    productId: Long = 1L,
    materials: List<ProductMaterialEnum> = listOf(K14, K18)
): UpsertProductMaterialVO {
    return fixtureMonkey.giveMeBuilder<UpsertProductMaterialVO>()
        .setExp(UpsertProductMaterialVO::productId, productId)
        .setExp(
            UpsertProductMaterialVO::upsertProductMaterialInternalVOs,
            upsertProductMaterialInternalVOFixture(materials)
        )
        .sample()
}

private fun upsertProductMaterialInternalVOFixture(
    materials: List<ProductMaterialEnum> = listOf(
        K14,
        K18
    )
): List<UpsertProductMaterialInternalVO> {
    return materials.map {
        fixtureMonkey.giveMeBuilder<UpsertProductMaterialInternalVO>()
            .setExp(UpsertProductMaterialInternalVO::id, 0L)
            .setExp(UpsertProductMaterialInternalVO::material, it)
            .setExp(UpsertProductMaterialInternalVO::additionalPrice, 1000)
            .sample()
    }
}