package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.productImage.saveProductImageVOFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import com.juloungjuloung.juju.s3.AwsS3Service
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductImageServiceFacadeTest : BehaviorSpec({

    val productImageRepository = mockk<ProductImageRepository>()
    every { productImageRepository.findByProduct(any()) } returns listOf()
    every { productImageRepository.saveAll(any()) } returns listOf()

    val awsS3Service = mockk<AwsS3Service>()
    val productImageService = ProductImageService(productImageRepository)
    val productImageServiceFacade = ProductImageServiceFacade(productImageService, awsS3Service)

    Given("상품 이미지 저장 요청이 있을 때") {
        Then("정상 요청이라면 성공한다") {
            val saveProductImageVO = saveProductImageVOFixture()

            shouldNotThrow<Exception> {
                productImageServiceFacade.saveAll(saveProductImageVO)
            }
        }

        Then("비정상(기본 이미지가 2개) 요청이라면, BusinessLogicException이 발생한다") {
            val saveProductImageVO = saveProductImageVOFixture(isMultiplePrimaryImage = true)

            val exception = shouldThrow<BusinessLogicException> {
                productImageServiceFacade.saveAll(saveProductImageVO)
            }
            exception.code shouldBe ApiResponseCode.PRODUCT_IMAGE_PRIMARY_NOT_ONE
        }

        Then("비정상(이미지 수가 10개 이상) 요청이라면, BusinessLogicException이 발생한다") {
            val saveProductImageVO = saveProductImageVOFixture(exceedMaxSize = true)

            val exception = shouldThrow<BusinessLogicException> {
                productImageServiceFacade.saveAll(saveProductImageVO)
            }
            exception.code shouldBe ApiResponseCode.PRODUCT_IMAGE_SIZE_EXCEED_MAX
        }
    }
})