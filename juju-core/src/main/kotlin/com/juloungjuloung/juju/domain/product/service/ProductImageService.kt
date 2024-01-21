package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.constants.ImageFileExtension
import com.juloungjuloung.juju.constants.S3PathPrefixConstant.PRODUCT_IMAGE
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.add
import com.juloungjuloung.juju.domain.product.changePrimary
import com.juloungjuloung.juju.domain.product.containsPrimary
import com.juloungjuloung.juju.domain.product.getPrimary
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_REMOVE_CONDITION_PRIMARY
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class ProductImageService(
    private val productRepository: ProductRepository,
    private val productImageRepository: ProductImageRepository
) {

    fun createUniquePath(): String {
        return PRODUCT_IMAGE.prefix + UUID.randomUUID().toString()
    }

    fun getVirtualImagePath(uniquePath: String, fileExtension: ImageFileExtension): String {
        // TODO: CloudFront URL 로 대체 예정 (ConfigurationProperties)
        return "https://test.com/" + uniquePath + fileExtension.extension
    }

    @Transactional
    fun saveAll(saveProductImageVO: SaveProductImageVO): List<Long> {
        val product = findProductById(saveProductImageVO.productId)

        val productImages = saveProductImageVO.toDomain()

        val savedProductImages = productImageRepository.findByProduct(saveProductImageVO.productId)
        val totalProductImages = savedProductImages.add(productImages)

        if (productImages.containsPrimary()) {
            changeThumbnailImageInProduct(product, productImages.getPrimary())
        }

        return productImageRepository.saveAll(totalProductImages.filterNotSaved())
    }

    private fun findProductById(productId: Long): Product {
        return productRepository.findById(productId)
    }

    private fun changeThumbnailImageInProduct(product: Product, primaryProductImage: ProductImage) {
        product.changePrimaryImage(primaryProductImage.imageUrl)
        productRepository.changePrimaryImage(product)
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

        if (productImages.containsPrimary()) {
            throw BusinessLogicException(PRODUCT_IMAGE_REMOVE_CONDITION_PRIMARY)
        }
    }

    private fun findByIds(productImageIds: List<Long>): List<ProductImage> {
        return productImageRepository.findByIds(productImageIds)
    }

    fun changePrimary(productId: Long, primaryProductImageId: Long): Long {
        val product = productRepository.findById(productId)
        val productImages = productImageRepository.findByProduct(productId)

        productImages.changePrimary(primaryProductImageId)
        productImageRepository.updatePrimary(productImages)

        changeThumbnailImageInProduct(product, productImages.getPrimary())

        return primaryProductImageId
    }
}