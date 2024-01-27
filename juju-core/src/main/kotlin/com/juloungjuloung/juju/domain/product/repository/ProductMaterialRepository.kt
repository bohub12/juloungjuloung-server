package com.juloungjuloung.juju.domain.product.repository

import com.juloungjuloung.juju.domain.product.ProductMaterial

interface ProductMaterialRepository {

    fun findByIds(productMaterialIds: List<Long>): List<ProductMaterial>

    fun findByProduct(productId: Long): List<ProductMaterial>

    fun saveAll(productMaterials: List<ProductMaterial>): List<Long>

    fun deleteAll(productMaterialIds: List<Long>)
}