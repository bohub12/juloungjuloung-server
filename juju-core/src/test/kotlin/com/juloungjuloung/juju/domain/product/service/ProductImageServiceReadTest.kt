package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.productimage.productImageCollectionFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductImageServiceReadTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productImageRepository = mockk<ProductImageRepository>()
    val productImageService = ProductImageService(productRepository, productImageRepository)

    Given("상품 이미지 읽기 요청이 들어왔을 때") {
        val productId = 1L
        val productImages = productImageCollectionFixture(productId = productId)

        Then("저장되어 있지 않은 상품ID가 들어오면 예외 발생") {
            every { productRepository.findById(productId) }.throws(BusinessLogicException(BAD_REQUEST_ID))

            val exception = shouldThrow<BusinessLogicException> {
                productImageService.read(productId)
            }
            exception.code shouldBe BAD_REQUEST_ID
        }

        Then("저장되어 있는 상품ID가 들어오면 정상 리턴") {
            every { productRepository.findById(productId) } returns productFixture(id = productId)
            every { productImageRepository.findByProduct(productId) } returns productImages

            val findProductImages = productImageService.read(productId)

            findProductImages shouldBe productImages
        }
    }
})