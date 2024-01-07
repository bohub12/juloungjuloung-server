package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.constants.S3ImageFileExtension.JPG
import com.juloungjuloung.juju.constants.S3PathPrefixConstant.PRODUCT_IMAGE
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.product.vo.GetPreSignedUrlVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
import com.juloungjuloung.juju.s3.AwsS3Service
import org.springframework.stereotype.Service

@Service
class ProductImageServiceFacade(
    private val productImageService: ProductImageService,
    private val productServiceImpl: ProductServiceImpl,
    private val awsS3Service: AwsS3Service
) {

    fun getPreSignedUrl(): GetPreSignedUrlVO {
        val virtualImagePath = productImageService.createVirtualPath()
        val preSignedUrl = awsS3Service.createPreSignedUrlForUpload(
            type = PRODUCT_IMAGE,
            path = virtualImagePath,
            fileExtension = JPG
        )

        return GetPreSignedUrlVO(virtualImagePath, preSignedUrl)
    }

    fun saveAll(saveProductImageVO: SaveProductImageVO): List<Long> {
        val product = productServiceImpl.readById(saveProductImageVO.productId)

        return productImageService.saveAll(saveProductImageVO.toDomain(product))
    }
}