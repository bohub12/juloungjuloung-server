package com.juloungjuloung.juju.domain.product.vo

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.enums.ProductMaterialEnum

data class UpsertProductMaterialVO(
    val productId: Long,
    val upsertProductMaterialInternalVOs: List<UpsertProductMaterialInternalVO>
) {
    fun toDomain(): List<ProductMaterial> {
        return upsertProductMaterialInternalVOs.map {
            ProductMaterial(
                id = it.id,
                productId = productId,
                material = it.material,
                additionalPrice = it.additionalPrice
            )
        }
    }
}

data class UpsertProductMaterialInternalVO(
    val id: Long = 0L,
    val material: ProductMaterialEnum,
    val additionalPrice: Int = 0
)