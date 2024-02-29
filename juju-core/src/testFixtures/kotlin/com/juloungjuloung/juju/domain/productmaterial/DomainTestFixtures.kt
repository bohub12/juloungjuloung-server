package com.juloungjuloung.juju.domain.productmaterial

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.enums.ProductMaterialEnum
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K18
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K22
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productMaterialFixture(
    id: Long = 1L,
    productId: Long = 1L,
    material: ProductMaterialEnum,
    additionalPrice: Int = 0
): ProductMaterial {
    return fixtureMonkey.giveMeBuilder<ProductMaterial>()
        .setExp(ProductMaterial::id, id)
        .setExp(ProductMaterial::productId, productId)
        .setExp(ProductMaterial::material, material)
        .setExp(ProductMaterial::additionalPrice, additionalPrice)
        .sample()
}

fun productMaterialCollectionFixture(
    ids: List<Long> = listOf(1L, 2L),
    productId: Long = 1L,
    materials: List<ProductMaterialEnum> = listOf(K18, K22),
    additionalPrices: List<Int> = listOf(0, 0)
): List<ProductMaterial> {
    return ids.mapIndexed { index, it ->
        productMaterialFixture(
            id = it,
            productId = productId,
            material = materials[index],
            additionalPrice = additionalPrices[index]
        )
    }
}