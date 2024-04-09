package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.service.ProductOptionCategoryService
import com.juloungjuloung.juju.domain.product.service.ProductOptionService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.productoption.productOptionCategoryFixture
import com.juloungjuloung.juju.domain.productoption.productOptionCollectionFixture
import com.juloungjuloung.juju.domain.productoption.upsertProductOptionVOFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductOptionServiceFacadeTest : BehaviorSpec({
    val productService = mockk<ProductServiceImpl>()
    val productOptionService = mockk<ProductOptionService>()
    val productOptionCategoryService = mockk<ProductOptionCategoryService>()
    val productOptionServiceFacade =
        ProductOptionServiceFacade(
            productOptionService = productOptionService,
            productOptionCategoryService = productOptionCategoryService,
            productService = productService
        )

    Given("상품 옵션 정보(카테고리, 옵션)을 저장/수정할 때") {
        val productId = 1L

        When("옵션 카테고리 안에 옵션이 없으면") {
            val upsertProductOptionVO = upsertProductOptionVOFixture(isEmptyOption = true)

            Then("예외 발생") {
                val exception =
                    shouldThrow<BusinessLogicException> {
                        productOptionServiceFacade.upsertProductOptions(upsertProductOptionVO)
                    }
                exception.code shouldBe PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION
            }
        }

        When("저장되어 있지 않은 상품ID를 가진 요청이라면") {
            val upsertProductOptionVO = upsertProductOptionVOFixture(productId = productId)
            every { productService.readById(productId) } throws BusinessLogicException(BAD_REQUEST_ID)

            Then("예외 발생") {
                val exception =
                    shouldThrow<BusinessLogicException> {
                        productOptionServiceFacade.upsertProductOptions(upsertProductOptionVO)
                    }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("저장되어 있지 않은 옵션카테고리ID를 가진 요청이라면") {
            val optionCategoryId = 1L
            val upsertProductOptionVO = upsertProductOptionVOFixture(optionCategoryId = optionCategoryId)

            every { productService.readById(any()) } returns productFixture()
            every { productOptionCategoryService.readById(optionCategoryId) } throws
                BusinessLogicException(
                    BAD_REQUEST_ID
                )

            Then("예외 발생") {
                val exception =
                    shouldThrow<BusinessLogicException> {
                        productOptionServiceFacade.upsertProductOptions(upsertProductOptionVO)
                    }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("저장되어 있지 않은 옵션ID를 포함한 요청이라면") {
            val optionCategoryId = 1L
            val optionIds = listOf(1L, 2L)
            val upsertProductOptionVO =
                upsertProductOptionVOFixture(
                    optionCategoryId = optionCategoryId,
                    optionIds = optionIds
                )

            every { productService.readById(any()) } returns productFixture()
            every { productOptionCategoryService.readById(optionCategoryId) } returns productOptionCategoryFixture()
            every { productOptionService.readByIds(optionIds) } returns listOf()

            Then("예외 발생") {
                val exception =
                    shouldThrow<BusinessLogicException> {
                        productOptionServiceFacade.upsertProductOptions(upsertProductOptionVO)
                    }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("정상 요청이라면") {
            val optionCategoryId = 1L
            val optionIds = listOf(1L, 2L)
            val upsertProductOptionVO =
                upsertProductOptionVOFixture(
                    optionCategoryId = optionCategoryId,
                    optionIds = optionIds
                )

            every { productService.readById(any()) } returns productFixture()
            every { productOptionCategoryService.readById(optionCategoryId) } returns
                productOptionCategoryFixture(
                    id = optionCategoryId
                )
            every { productOptionService.readByIds(optionIds) } returns
                productOptionCollectionFixture(
                    ids = optionIds,
                    optionCategoryId = optionCategoryId
                )

            every { productOptionCategoryService.upsert(any()) } returns optionCategoryId
            every { productOptionService.upsert(any()) } returns optionIds

            productOptionServiceFacade.upsertProductOptions(upsertProductOptionVO)

            Then("정상 실행") {
                verify { productOptionCategoryService.upsert(any()) }
                verify { productOptionService.upsert(any()) }
            }
        }
    }
})