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

fun List<ProductMaterial>.combineForValidation(productMaterials: List<ProductMaterial>): ProductMaterials {
    return ProductMaterials(this + productMaterials)
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
}