package com.juloungjuloung.juju.s3

import com.juloungjuloung.juju.constants.ImageFileExtension
import com.juloungjuloung.juju.properties.AwsCloudFrontProperties
import com.juloungjuloung.juju.properties.AwsS3Properties
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.time.Duration

@Service
class AwsS3Service(
    private val awsS3Properties: AwsS3Properties,
    private val awsCloudFrontProperties: AwsCloudFrontProperties,
    private val s3PreSigner: S3Presigner
) {

    fun createPreSignedUrlForUpload(
        path: String,
        fileExtension: ImageFileExtension
    ): String {
        if (path.contains(".")) {
            throw IllegalStateException("path can not contain file extension")
        }

        val putObjectRequest = PutObjectRequest.builder()
            .bucket(awsS3Properties.bucket)
            .key(path + fileExtension.extension)
            .contentType(fileExtension.contentType)
            .build()

        val preSignRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(5)) // The URL will expire in 5 minutes.
            .putObjectRequest(putObjectRequest)
            .build()

        return s3PreSigner.presignPutObject(preSignRequest).url().toString()
    }

    fun getVirtualImagePath(uniquePath: String, fileExtension: ImageFileExtension): String {
        return awsCloudFrontProperties.imageDistributionDomain + "/" + uniquePath + fileExtension.extension
    }
}