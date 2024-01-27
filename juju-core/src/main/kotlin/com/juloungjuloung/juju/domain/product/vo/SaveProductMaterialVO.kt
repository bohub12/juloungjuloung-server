package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.enums.ProductMaterialEnum

data class SaveProductMaterialVO(
    val productId: Long,
    val saveProductMaterialInternalVOs: List<SaveProductMaterialInternalVO>
) {
    fun toDomain(): List<ProductMaterial> {
        return saveProductMaterialInternalVOs.map {
            ProductMaterial(productId = productId, material = it.material, additionalPrice = it.additionalPrice)
        }
    }
}

data class SaveProductMaterialInternalVO(
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
)