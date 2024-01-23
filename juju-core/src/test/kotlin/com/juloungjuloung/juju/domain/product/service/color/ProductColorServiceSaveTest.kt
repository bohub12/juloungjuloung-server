package com.juloungjuloung.juju.domain.product.service.color

import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.repository.ProductColorRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.service.ProductColorService
import com.juloungjuloung.juju.domain.productcolor.productColorCollectionFixture
import com.juloungjuloung.juju.domain.productcolor.saveProductColorVOFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_COLOR_DUPLICATE_CODE_IN_SAME_PRODUCT
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductColorServiceSaveTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productColorRepository = mockk<ProductColorRepository>()
    val productColorService = ProductColorService(productRepository, productColorRepository)

    Given("상품의 적용 가능한 색상을 저장할 때") {
        val productId = 1L
        When("저장되어 있지 않은 상품ID를 가진 요청이라면") {
            val saveProductColorVO = saveProductColorVOFixture(productId = productId)

            every { productRepository.findById(productId) } throws BusinessLogicException(BAD_REQUEST_ID)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productColorService.saveAll(saveProductColorVO)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("중복된 색상이 포함되어 있으면") {
            val saveProductColorVO = saveProductColorVOFixture(productId = productId, duplicatedColor = true)
            val productColors = productColorCollectionFixture(productId = productId)

            every { productRepository.findById(productId) } returns productFixture(id = productId)
            every { productColorRepository.findByProduct(productId) } returns productColors

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productColorService.saveAll(saveProductColorVO)
                }
                exception.code shouldBe PRODUCT_COLOR_DUPLICATE_CODE_IN_SAME_PRODUCT
            }
        }

        When("정상 요청이라면") {
            val saveProductColorVO = saveProductColorVOFixture(productId = productId)

            every { productRepository.findById(productId) } returns productFixture(id = productId)
            every { productColorRepository.findByProduct(productId) } returns listOf()
            every { productColorRepository.saveAll(any()) } returns List(
                saveProductColorVO.saveProductColorInternalVOs.size
            ) { index -> (index + 1).toLong() }

            Then("정상 실행") {
                shouldNotThrow<Exception> {
                    productColorService.saveAll(saveProductColorVO)
                }
            }
        }
    }
})