package com.juloungjuloung.juju.domain.product.service.material

import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.repository.ProductMaterialRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.service.ProductMaterialService
import com.juloungjuloung.juju.domain.productmaterial.productMaterialCollectionFixture
import com.juloungjuloung.juju.domain.productmaterial.saveProductMaterialVOFixture
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

class ProductMaterialServiceSaveTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productMaterialRepository = mockk<ProductMaterialRepository>()
    val productMaterialService = ProductMaterialService(productRepository, productMaterialRepository)

    Given("적용 가능한 상품 재질을 저장할 때") {
        val productId = 1L
        val saveProductMaterialVO = saveProductMaterialVOFixture(productId = productId, materials = listOf(K18, K22))

        When("저장되어 있지 않은 상품ID를 가진 요청이라면") {
            every { productRepository.findById(productId) } throws BusinessLogicException(BAD_REQUEST_ID)

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productMaterialService.saveAll(saveProductMaterialVO)
                }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }

        When("중복된 material 이 있는 요청이라면") {
            val savedProductMaterials = productMaterialCollectionFixture(productId = productId, materials = listOf(K18))

            every { productRepository.findById(productId) } returns productFixture(id = productId)
            every { productMaterialRepository.findByProduct(productId) } returns savedProductMaterials

            Then("예외 발생") {
                val exception = shouldThrow<BusinessLogicException> {
                    productMaterialService.saveAll(saveProductMaterialVO)
                }
                exception.code shouldBe PRODUCT_MATERIAL_DUPLICATE_CODE_IN_SAME_PRODUCT
            }
        }

        When("정상적인 요청이 들어오면") {
            every { productRepository.findById(productId) } returns productFixture(id = productId)
            every { productMaterialRepository.findByProduct(productId) } returns listOf()
            every { productMaterialRepository.saveAll(any()) } returns List(
                saveProductMaterialVO.saveProductMaterialInternalVOs.size
            ) { index -> (index + 1).toLong() }

            Then("정상 실행") {
                shouldNotThrow<Exception> {
                    productMaterialService.saveAll(saveProductMaterialVO)
                }

                verify { productMaterialRepository.saveAll(any()) }
            }
        }
    }
})