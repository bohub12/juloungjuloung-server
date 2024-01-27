package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.constants.ImageFileExtension
import com.juloungjuloung.juju.constants.S3PathPrefixConstant
import com.juloungjuloung.juju.domain.product.vo.GetPreSignedUrlVO
import com.juloungjuloung.juju.s3.AwsS3Service
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreatePreSignedUrlServiceFacade(
    private val awsS3Service: AwsS3Service
) {
    fun getPreSignedUrl(): GetPreSignedUrlVO {
        val uniquePath = createUniquePath()
        val fileExtension = ImageFileExtension.JPG

        val preSignedUrl = awsS3Service.createPreSignedUrlForUpload(uniquePath, fileExtension)
        val virtualImagePath = awsS3Service.getVirtualImagePath(uniquePath, fileExtension)

        return GetPreSignedUrlVO(virtualImagePath, preSignedUrl)
    }

    private fun createUniquePath(): String {
        return S3PathPrefixConstant.PRODUCT_IMAGE.prefix + UUID.randomUUID().toString()
    }
}