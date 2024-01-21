package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.productImage.productImageCollectionFixture
import com.juloungjuloung.juju.domain.productImage.saveProductImageVOFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductImageServiceSaveTest : BehaviorSpec({

    val productRepository = mockk<ProductRepository>()
    val productImageRepository = mockk<ProductImageRepository>(relaxed = true)
    val productImageService = ProductImageService(productRepository, productImageRepository)

    /**
     * 상품 이미지 저장
     */
    Given("상품 이미지들을 저장할 때") {
        val productId = 1L
        every { productRepository.findById(any()) } returns productFixture(id = productId)
        every { productRepository.changePrimaryImage(any()) } returns productId

        When("저장되어 있는 이미지가 없고") {
            every { productImageRepository.findByProduct(productId = productId) } returns listOf()
            every { productImageRepository.saveAll(any()) } returns listOf()

            Then("새로운 요청이 문제가 없다면 정상 실행") {
                val saveProductImageVO = saveProductImageVOFixture(productId = productId)

                shouldNotThrow<Exception> {
                    productImageService.saveAll(saveProductImageVO)
                }
            }

            Then("기본 이미지가 2개 이상이라면 예외 발생") {
                val saveProductImageVO = saveProductImageVOFixture(isMultiplePrimaryImage = true, productId = productId)

                val exception = shouldThrow<BusinessLogicException> {
                    productImageService.saveAll(saveProductImageVO)
                }
                exception.code shouldBe ApiResponseCode.PRODUCT_IMAGE_PRIMARY_NOT_ONE
            }

            Then("최대 이미지 개수를 넘기는 요청이라면 예외 발생") {
                val saveProductImageVO = saveProductImageVOFixture(exceedMaxSize = true, productId = productId)

                val exception = shouldThrow<BusinessLogicException> {
                    productImageService.saveAll(saveProductImageVO)
                }
                exception.code shouldBe ApiResponseCode.PRODUCT_IMAGE_SIZE_EXCEED_MAX
            }
        }
        When("저장되어 있는 이미지가 있고") {
            every { productImageRepository.findByProduct(productId = productId) } returns
                productImageCollectionFixture(productId = productId)

            Then("새로운 요청 중 기본 이미지가 있다면 예외가 발생한다") {
                val saveProductImageVOIncludePrimary = saveProductImageVOFixture(productId = productId)

                val exception = shouldThrow<BusinessLogicException> {
                    productImageService.saveAll(saveProductImageVOIncludePrimary)
                }
                exception.code shouldBe ApiResponseCode.PRODUCT_IMAGE_PRIMARY_NOT_ONE
            }

            Then("새로운 요청과 저장된 이미지들이 최대 개수를 넘기면 예외가 발생한다") {
                val saveProductImageVOExceedMaxSize =
                    saveProductImageVOFixture(exceedMaxSize = true, productId = productId)

                val exception = shouldThrow<BusinessLogicException> {
                    productImageService.saveAll(saveProductImageVOExceedMaxSize)
                }
                exception.code shouldBe ApiResponseCode.PRODUCT_IMAGE_SIZE_EXCEED_MAX
            }
        }
    }
})