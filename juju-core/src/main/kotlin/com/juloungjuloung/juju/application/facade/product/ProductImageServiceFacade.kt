package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.product.validate
import com.juloungjuloung.juju.domain.product.vo.UpsertProductImageVO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductImageServiceFacade(
    private val productImageService: ProductImageService,
    private val productService: ProductServiceImpl
) {

    @Transactional(readOnly = true)
    fun readByProduct(productId: Long): List<ProductImage> {
        findProductOrException(productId)

        return productImageService.readByProduct(productId)
    }

    private fun findProductOrException(productId: Long): Product {
        return productService.readById(productId)
    }

    @Transactional
    fun upsertProductImages(upsertProductImageVO: UpsertProductImageVO): List<Long> {
        val productImages = upsertProductImageVO.toDomain().validate()

        deleteUnassociatedProductImages(upsertProductImageVO.productId, productImages.getProductImageIds())

        if (productImages.containsThumbnail()) {
            productService.changeThumbnailImage(upsertProductImageVO.productId, productImages.getThumbnail().imageUrl)
        }

        return productImageService.upsert(upsertProductImageVO)
    }

    private fun deleteUnassociatedProductImages(productId: Long, associatedProductImageIds: List<Long>) {
        productImageService.deleteUnassociatedProductImages(productId, associatedProductImageIds)
    }
}