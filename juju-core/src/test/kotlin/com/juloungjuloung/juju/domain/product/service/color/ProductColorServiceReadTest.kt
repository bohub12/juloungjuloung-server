package com.juloungjuloung.juju.domain.product.service.color

import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.repository.ProductColorRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.service.ProductColorService
import com.juloungjuloung.juju.domain.productcolor.productColorCollectionFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductColorServiceReadTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productColorRepository = mockk<ProductColorRepository>()
    val productColorService = ProductColorService(productRepository, productColorRepository)

    Given("상품에 적용가능한 색상을 조회할 때") {
        val productId = 1L

        When("저장되어 있지 않은 상품ID로 조회한다면") {
            every { productRepository.findById(productId) } throws BusinessLogicException(BAD_REQUEST_ID)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productColorService.findByProduct(productId)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("정상 요청이라면") {
            every { productRepository.findById(productId) } returns productFixture(id = productId)
            every { productColorRepository.findByProduct(productId) } returns productColorCollectionFixture(
                productId = productId
            )

            Then("정상 실행") {
                shouldNotThrow<Exception> {
                    productColorService.findByProduct(productId)
                }
            }
        }
    }
})