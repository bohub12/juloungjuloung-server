package com.juloungjuloung.juju.domain.productmaterial

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.enums.ProductMaterialEnum
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K18
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K22
import com.juloungjuloung.juju.fixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp

fun productMaterialFixture(id: Long = 1L, productId: Long = 1L, material: ProductMaterialEnum): ProductMaterial {
    return fixtureMonkey.giveMeBuilder<ProductMaterial>()
        .setExp(ProductMaterial::id, id)
        .setExp(ProductMaterial::productId, productId)
        .setExp(ProductMaterial::material, material)
        .sample()
}

fun productMaterialCollectionFixture(
    productId: Long = 1L,
    materials: List<ProductMaterialEnum> = listOf(K18, K22)
): List<ProductMaterial> {
    return materials.mapIndexed { index, it ->
        productMaterialFixture(id = index + 1L, productId = productId, material = it)
    }
}