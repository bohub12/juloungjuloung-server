package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.product.vo.UpsertProductImageVO
import com.juloungjuloung.juju.domain.productimage.upsertProductImageVOFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_SIZE_EXCEED_MAX
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_THUMBNAIL_NOT_ONE
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductImageServiceFacadeTest : BehaviorSpec({

    val productService = mockk<ProductServiceImpl>()
    val productImageService = mockk<ProductImageService>()
    val productImageServiceFacade = ProductImageServiceFacade(productImageService, productService)

    Given("상품 이미지들을 저장 및 수정할 때") {
        When("썸네일 이미지가 아닌 기본 이미지를 등록하려하면") {
            Then("예외 발생") {
                // TODO 추후 검증로직 추가예정
            }
        }

        When("상품 이미지 최대 저장 개수를 넘긴다면") {
            val request = upsertProductImageVOFixture(exceedMaxSize = true)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productImageServiceFacade.upsertProductImages(request)
                }
                exception.code shouldBe PRODUCT_IMAGE_SIZE_EXCEED_MAX
            }
        }

        When("썸네일 이미지가 여러 장이라면") {
            val request = upsertProductImageVOFixture(isMultipleThumbnailImage = true)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productImageServiceFacade.upsertProductImages(request)
                }
                exception.code shouldBe PRODUCT_IMAGE_THUMBNAIL_NOT_ONE
            }
        }

        When("정상 요청이 온다면") {
            val request = upsertProductImageVOFixture()

            every { productImageService.deleteUnassociatedProductImages(any(), any()) } returns Unit
            every { productService.changeThumbnailImage(any(), any()) } returns Unit
            every { productImageService.upsert(request) } returns List(request.upsertProductImageInternalVOs.size) {
                it.toLong()
            }

            Then("정상 실행") {
                shouldNotThrow<Exception> {
                    productImageServiceFacade.upsertProductImages(request)
                }
            }

            Then("상품 엔티티의 썸네일 이미지를 교체") {
                verify { productService.changeThumbnailImage(any(), any()) }
            }
        }
    }

    Given("상품 이미지를 모두 삭제하기 위해") {
        When("요청바디를 빈 배열로 보내면") {
            val request = UpsertProductImageVO(productId = 1L, upsertProductImageInternalVOs = listOf())

            every { productImageService.deleteUnassociatedProductImages(any(), any()) } returns Unit
            every { productService.changeThumbnailImage(any(), any()) } returns Unit
            every { productImageService.upsert(request) } returns listOf()

            Then("정상 실행") {
                shouldNotThrow<Exception> {
                    productImageServiceFacade.upsertProductImages(request)
                }
                verify { productImageService.deleteUnassociatedProductImages(any(), any()) }
                verify { productImageService.upsert(request) }
            }
        }
    }
})