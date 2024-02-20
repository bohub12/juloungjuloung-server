package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.changeThumbnail
import com.juloungjuloung.juju.domain.product.combineForValidation
import com.juloungjuloung.juju.domain.product.containsThumbnail
import com.juloungjuloung.juju.domain.product.getThumbnail
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductImageService(
    private val productRepository: ProductRepository,
    private val productImageRepository: ProductImageRepository
) {

    fun read(productId: Long): List<ProductImage> {
        findProductOrException(productId)

        return productImageRepository.findByProduct(productId)
    }

    @Transactional
    fun saveAll(saveProductImageVO: SaveProductImageVO): List<Long> {
        validateSaveCondition(saveProductImageVO)

        val product = findProductOrException(saveProductImageVO.productId)
        val productImages = saveProductImageVO.toDomain()

        if (productImages.containsThumbnail()) {
            changeThumbnailImageInProduct(product, productImages.getThumbnail())
        }

        return productImageRepository.saveAll(productImages)
    }

    private fun validateSaveCondition(saveProductImageVO: SaveProductImageVO) {
        findProductOrException(saveProductImageVO.productId)

        val productImages = saveProductImageVO.toDomain()
        val savedProductImages = productImageRepository.findByProduct(saveProductImageVO.productId)

        savedProductImages.combineForValidation(productImages)
    }

    private fun findProductOrException(productId: Long): Product {
        return productRepository.findById(productId)
    }

    private fun changeThumbnailImageInProduct(product: Product, productThumbnailImage: ProductImage) {
        product.changeThumbnailImage(productThumbnailImage.imageUrl)
        productRepository.changeThumbnailImage(product)
    }

    @Transactional
    fun delete(productImageIds: List<Long>): Boolean {
        validateDeleteCondition(productImageIds)

        productImageRepository.deleteAll(productImageIds)
        return true
    }

    private fun validateDeleteCondition(productImageIds: List<Long>) {
        val productImages = findByIds(productImageIds)
        if (productImages.size != productImageIds.size) {
            throw BusinessLogicException(BAD_REQUEST_ID)
        }

        if (productImages.containsThumbnail()) {
            throw BusinessLogicException(PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL)
        }
    }

    private fun findByIds(productImageIds: List<Long>): List<ProductImage> {
        return productImageRepository.findByIds(productImageIds)
    }

    @Transactional
    fun changeThumbnail(productId: Long, productThumbnailImageId: Long): Long {
        val product = productRepository.findById(productId)
        val productImages = productImageRepository.findByProduct(productId)

        productImages.changeThumbnail(productThumbnailImageId)
        productImageRepository.changeThumbnail(productImages)

        changeThumbnailImageInProduct(product, productImages.getThumbnail())

        return productThumbnailImageId
    }
}