package com.juloungjuloung.juju.domain.product

import com.juloungjuloung.juju.enums.ProductMaterialEnum
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_MATERIAL_DUPLICATE_CODE_IN_SAME_PRODUCT
import java.time.LocalDateTime

data class ProductMaterial(
    val id: Long = 0L,
    val productId: Long,
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

fun List<ProductMaterial>.validate(): ProductMaterials {
    return ProductMaterials(this)
}

fun List<ProductMaterial>.filterPersisted(): List<ProductMaterial> {
    return this.filter { it.id != 0L }
}

fun List<ProductMaterial>.filterNotPersisted(): List<ProductMaterial> {
    return this.filter { it.id == 0L }
}

data class ProductMaterials(
    val productMaterials: List<ProductMaterial>
) {
    init {
        requireNoDuplicateMaterials(productMaterials)
    }

    private fun requireNoDuplicateMaterials(productMaterials: List<ProductMaterial>) {
        val uniqueMaterials = productMaterials.map(ProductMaterial::material).toSet()

        if (productMaterials.size != uniqueMaterials.size) {
            throw BusinessLogicException(PRODUCT_MATERIAL_DUPLICATE_CODE_IN_SAME_PRODUCT)
        }
    }

    fun getProductMaterialIds(): List<Long> {
        return productMaterials.map { it.id }
    }
}