package com.juloungjuloung.juju.s3

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.time.Duration

@Service
class AwsS3Service(
    @Value("\${aws.s3.bucket}")
    private val bucket: String,
    private val s3PreSigner: S3Presigner
) {

    fun createPreSignedUrlForUpload(path: String): String {
        val putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(path)
            .contentType("image/jpeg")
            .build()

        val preSignRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(5)) // The URL will expire in 5 minutes.
            .putObjectRequest(putObjectRequest)
            .build()

        return s3PreSigner.presignPutObject(preSignRequest).url().toString()
    }
}