package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.filterPersisted
import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.service.ProductColorService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.productcolor.productColorCollectionFixture
import com.juloungjuloung.juju.domain.productcolor.upsertProductColorVOFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_COLOR_DUPLICATE_CODE_IN_SAME_PRODUCT
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductColorServiceFacadeTest : BehaviorSpec({
    val productColorService = mockk<ProductColorService>()
    val productService = mockk<ProductServiceImpl>()
    val productColorServiceFacade = ProductColorServiceFacade(productColorService, productService)

    Given("상품의 적용 가능한 색상을 저장할 때") {
        val productId = 1L

        When("중복된 색상이 포함되어 있으면") {
            val request = upsertProductColorVOFixture(productId = productId, duplicatedColor = true)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productColorServiceFacade.upsertProductColors(request)
                }
                exception.code shouldBe PRODUCT_COLOR_DUPLICATE_CODE_IN_SAME_PRODUCT
            }
        }

        When("저장되어 있지 않은 상품ID를 가진 요청이라면") {
            val request = upsertProductColorVOFixture(productId = productId)
            every { productService.readById(productId) } throws BusinessLogicException(BAD_REQUEST_ID)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productColorServiceFacade.upsertProductColors(request)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("정상 요청이라면") {
            val request = upsertProductColorVOFixture(productId = productId)
            every { productService.readById(productId) } returns productFixture()
            every { productColorService.findByProduct(productId = productId) } returns listOf()
            every { productColorService.deleteAll(any()) } returns true
            every { productColorService.upsertAll(any()) } returns List(request.upsertProductColorInternalVOs.size) {
                it.toLong()
            }

            productColorServiceFacade.upsertProductColors(request)

            Then("정상 실행") {
                verify { productColorService.deleteAll(request.toDomain().filterPersisted().map { it.id }) }
                verify { productColorService.upsertAll(request) }
            }
        }
    }

    Given("상품에 적용가능한 색상을 조회할 때") {
        val productId = 1L

        When("저장되어 있지 않은 상품ID로 조회한다면") {
            every { productService.readById(productId) } throws BusinessLogicException(BAD_REQUEST_ID)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productColorServiceFacade.findByProduct(productId)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("정상 요청이라면") {
            val persistedProductColors = productColorCollectionFixture()

            every { productService.readById(productId) } returns productFixture()
            every { productColorService.findByProduct(productId) } returns persistedProductColors

            val productColors = productColorService.findByProduct(productId)

            Then("정상 실행") {
                productColors shouldContainExactlyInAnyOrder persistedProductColors
            }
        }
    }
})