package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.constants.ImageFileExtension
import com.juloungjuloung.juju.constants.S3PathPrefixConstant.PRODUCT_IMAGE
import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.ProductImages
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class ProductImageService(
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
    fun saveAll(productImages: List<ProductImage>): List<Long> {
        ProductImages.combineForValidation(
            productImages,
            productImageRepository.findByProduct(productImages[0].productId)
        )

        return productImageRepository.saveAll(productImages)
    }
}