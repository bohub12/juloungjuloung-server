package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.constants.ImageFileExtension
import com.juloungjuloung.juju.constants.S3PathPrefixConstant.PRODUCT_IMAGE
import com.juloungjuloung.juju.domain.product.add
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
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
    fun saveAll(saveProductImageVO: SaveProductImageVO): List<Long> {
        val productImages = saveProductImageVO.toDomain()

        val savedProductImages = productImageRepository.findByProduct(saveProductImageVO.productId)
        val totalProductImages = savedProductImages.add(productImages)

        return productImageRepository.saveAll(totalProductImages.filterNotSaved())
    }
}