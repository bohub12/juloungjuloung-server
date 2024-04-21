package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductServiceImplTest : BehaviorSpec({

    val productRepository = mockk<ProductRepository>(relaxed = true)
    val sut = ProductServiceImpl(productRepository)

    Given("상품 대표 이미지를 삭제할 때") {
        val productId = 1L

        When("전시 중인 상품이라면") {
            val product = productFixture(id = productId, isDisplay = true)
            every { productRepository.findById(productId) } returns product

            Then("예외 발생") {
                val exception =
                    shouldThrow<BusinessLogicException> {
                        sut.changeThumbnailImage(productId, null)
                    }
                exception.code shouldBe PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL
            }
        }

        When("전시 중이지 않은 상품이라면") {
            val product = productFixture(id = productId, isDisplay = false)
            every { productRepository.findById(productId) } returns product

            sut.changeThumbnailImage(productId, null)

            Then("정상 실행") {
                product.thumbnailImage shouldBe null
                verify { productRepository.changeThumbnailImage(product) }
            }
        }
    }

    Given("상품 대표 이미지를 다른 이미지로 변경하려할 때") {
        val productId = 1L
        val thumbnailImageUrl = "https://test.com/test.png"

        val product = productFixture(id = productId)
        every { productRepository.findById(productId) } returns product

        When("상품 대표 이미지를 변경하면") {
            sut.changeThumbnailImage(productId, thumbnailImageUrl)

            Then("정상 실행") {
                product.thumbnailImage shouldBe thumbnailImageUrl
                verify { productRepository.changeThumbnailImage(product) }
            }
        }
    }
})