package com.juloungjuloung.juju.s3

import com.juloungjuloung.juju.constants.S3ImageFileExtension
import com.juloungjuloung.juju.constants.S3PathPrefixConstant
import com.juloungjuloung.juju.properties.AwsS3Properties
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import software.amazon.awssdk.services.s3.presigner.S3Presigner
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.lang.IllegalStateException
import java.util.UUID

class AwsS3ServiceTest : BehaviorSpec({

    val awsS3Properties: AwsS3Properties = mockk<AwsS3Properties>()
    val s3PreSigner: S3Presigner = mockk<S3Presigner>()
    val awsS3Service = AwsS3Service(awsS3Properties, s3PreSigner)

    every { awsS3Properties.bucket } returns "test-s3"
    every {
        s3PreSigner.presignPutObject(any<PutObjectPresignRequest>()).url().toString()
    } returns "https://test-s3.s3.eu-east-2.com/test/1.png"

    Given("S3 presigned url 을 생성하려 할 때") {
        When("잘못된 path(확장자 포함)가 들어오면") {
            Then("exception이 발생한다") {
                shouldThrow<IllegalStateException> {
                    awsS3Service.createPreSignedUrlForUpload(
                        S3PathPrefixConstant.MEMBER,
                        "1.png",
                        S3ImageFileExtension.JPG
                    )
                }
            }
        }

        When("정상적인 파라미터가 들어오면") {
            Then("정상적으로 url을 생성한다") {
                val preSignedUrl = awsS3Service.createPreSignedUrlForUpload(
                    S3PathPrefixConstant.MEMBER,
                    UUID.randomUUID().toString(),
                    S3ImageFileExtension.JPG
                )

                preSignedUrl shouldNotBe null
            }
        }
    }
})