package com.juloungjuloung.juju.domain.product.service.color

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
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify

class ProductColorServiceDeleteTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productColorRepository = mockk<ProductColorRepository>()
    val productColorService = ProductColorService(productRepository, productColorRepository)

    Given("상품 색상을 삭제하려 할 때") {
        val productColorIds = listOf(1L, 2L)

        When("존재하지 않는 ID로 접근하면") {
            every { productColorRepository.findByIds(productColorIds) } returns listOf()

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productColorService.deleteAll(productColorIds)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("정상 요청이라면") {
            every { productColorRepository.findByIds(productColorIds) } returns productColorCollectionFixture(
                ids = productColorIds
            )
            every { productColorRepository.deleteAll(productColorIds) } just Runs

            Then("정살 실행") {
                shouldNotThrow<Exception> {
                    productColorService.deleteAll(productColorIds)
                }

                verify { productColorRepository.deleteAll(productColorIds) }
            }
        }
    }
})