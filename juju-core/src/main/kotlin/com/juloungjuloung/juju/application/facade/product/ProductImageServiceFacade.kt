package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.constants.ImageFileExtension.JPG
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.product.vo.GetPreSignedUrlVO
import com.juloungjuloung.juju.domain.product.vo.SaveProductImageVO
import com.juloungjuloung.juju.s3.AwsS3Service
import org.springframework.stereotype.Service

@Service
class ProductImageServiceFacade(
    private val productImageService: ProductImageService,
    private val awsS3Service: AwsS3Service
) {

    fun getPreSignedUrl(): GetPreSignedUrlVO {
        val uniquePath = productImageService.createUniquePath()
        val fileExtension = JPG

        val preSignedUrl = awsS3Service.createPreSignedUrlForUpload(uniquePath, fileExtension)
        val virtualImagePath = productImageService.getVirtualImagePath(uniquePath, fileExtension)

        return GetPreSignedUrlVO(virtualImagePath, preSignedUrl)
    }

    fun saveAll(saveProductImageVO: SaveProductImageVO): List<Long> {
        return productImageService.saveAll(saveProductImageVO.toDomain())
    }
}