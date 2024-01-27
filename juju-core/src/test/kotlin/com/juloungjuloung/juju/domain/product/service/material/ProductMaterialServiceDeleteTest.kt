package com.juloungjuloung.juju.domain.product.service.material

import com.juloungjuloung.juju.domain.product.repository.ProductMaterialRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.service.ProductMaterialService
import com.juloungjuloung.juju.domain.productmaterial.productMaterialCollectionFixture
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K18
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K22
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

class ProductMaterialServiceDeleteTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productMaterialRepository = mockk<ProductMaterialRepository>()
    val productMaterialService = ProductMaterialService(productRepository, productMaterialRepository)

    Given("상품에 적용가능한 재질을 삭제하려할 때") {
        val productMaterialIds = listOf(1L, 2L)

        When("저장되어 있지 않은 상품 재질 ID가 있다면") {
            every { productMaterialRepository.findByIds(productMaterialIds) } returns listOf()

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productMaterialService.deleteAll(productMaterialIds)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("정상 요청이라면") {
            every { productMaterialRepository.findByIds(productMaterialIds) } returns productMaterialCollectionFixture(
                materials = listOf(K18, K22)
            )
            every { productMaterialRepository.deleteAll(productMaterialIds) } just Runs

            Then("정상 실행") {
                shouldNotThrow<Exception> {
                    productMaterialService.deleteAll(productMaterialIds)
                }
                verify { productMaterialRepository.deleteAll(productMaterialIds) }
            }
        }
    }
})