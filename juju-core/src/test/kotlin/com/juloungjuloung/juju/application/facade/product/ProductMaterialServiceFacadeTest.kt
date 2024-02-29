package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.domain.product.filterPersisted
import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.service.ProductMaterialService
import com.juloungjuloung.juju.domain.product.service.impl.ProductServiceImpl
import com.juloungjuloung.juju.domain.productmaterial.upsertProductMaterialVOFixture
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K14
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K18
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K22
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_MATERIAL_DUPLICATE_CODE_IN_SAME_PRODUCT
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductMaterialServiceFacadeTest : BehaviorSpec({

    val productService = mockk<ProductServiceImpl>()
    val productMaterialService = mockk<ProductMaterialService>()
    val productMaterialServiceFacade = ProductMaterialServiceFacade(productMaterialService, productService)

    Given("적용 가능한 상품 재질을 저장할 때") {
        val productId = 1L

        When("저장되어 있지 않은 상품ID를 가진 요청이라면") {
            val upsertVO = upsertProductMaterialVOFixture(productId = productId, materials = listOf(K18, K22))

            every { productService.readById(productId) } throws BusinessLogicException(BAD_REQUEST_ID)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productMaterialServiceFacade.upsertAll(upsertVO)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("중복된 material 이 있는 요청이라면") {
            val upsertVO = upsertProductMaterialVOFixture(productId = productId, materials = listOf(K18, K18))

            every { productService.readById(productId) } returns productFixture(id = productId)
            every { productMaterialService.readProductMaterials(productId) } returns emptyList()

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productMaterialServiceFacade.upsertAll(upsertVO)
                }
                exception.code shouldBe PRODUCT_MATERIAL_DUPLICATE_CODE_IN_SAME_PRODUCT
            }
        }

        When("정상적인 요청이 들어오면") {
            val upsertVO = upsertProductMaterialVOFixture(productId = productId, materials = listOf(K14, K18))

            every { productService.readById(productId) } returns productFixture(id = productId)

            every { productMaterialService.readProductMaterials(productId) } returns listOf()
            every { productMaterialService.deleteAll(upsertVO.toDomain().filterPersisted().map { it.id }) } returns true
            every { productMaterialService.upsertAll(any()) } returns List(
                upsertVO.upsertProductMaterialInternalVOs.size
            ) {
                it.toLong()
            }

            Then("정상 실행") {
                shouldNotThrow<Exception> {
                    productMaterialServiceFacade.upsertAll(upsertVO)
                }

                verify { productMaterialService.deleteAll(upsertVO.toDomain().filterPersisted().map { it.id }) }
                verify { productMaterialService.upsertAll(upsertVO) }
            }
        }
    }
})