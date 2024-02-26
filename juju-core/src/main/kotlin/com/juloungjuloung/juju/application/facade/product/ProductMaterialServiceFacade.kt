package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.domain.product.service.ProductMaterialService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.product.validate
import com.juloungjuloung.juju.domain.product.vo.UpsertProductMaterialVO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductMaterialServiceFacade(
    private val productMaterialService: ProductMaterialService,
    private val productService: ProductServiceImpl
) {

    fun readProductMaterialsByProduct(productId: Long): List<ProductMaterial> {
        return productMaterialService.readProductMaterials(productId)
    }

    @Transactional
    fun upsertAll(upsertProductMaterialVO: UpsertProductMaterialVO): List<Long> {
        val productMaterials = upsertProductMaterialVO.toDomain().validate()

        findProductOrException(upsertProductMaterialVO.productId)
        deletePersistedProductMaterialsExcludeRequest(
            upsertProductMaterialVO.productId,
            productMaterials.getProductMaterialIds()
        )

        return productMaterialService.upsertAll(upsertProductMaterialVO)
    }

    private fun findProductOrException(productId: Long): Product {
        return productService.readById(productId)
    }

    private fun deletePersistedProductMaterialsExcludeRequest(
        productId: Long,
        requestedProductMaterialIds: List<Long>
    ) {
        val persistedProductMaterials = productMaterialService.readProductMaterials(productId).map { it.id }

        productMaterialService.deleteAll(
            persistedProductMaterials.filterNot { requestedProductMaterialIds.contains(it) }
        )
    }
}