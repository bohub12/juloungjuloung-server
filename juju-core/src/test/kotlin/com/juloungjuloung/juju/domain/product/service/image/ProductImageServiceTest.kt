package com.juloungjuloung.juju.domain.product.service.image

import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.productimage.upsertProductImageVOFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductImageServiceTest : BehaviorSpec({

    val productImageRepository = mockk<ProductImageRepository>()
    val productImageService = ProductImageService(productImageRepository)

    Given("상품 이미지들을 수정할 때") {
        val upsertProductImageVOFixture = upsertProductImageVOFixture(includedPersistedRequest = true)

        When("요청 바디에 삭제된 상품이미지ID가 있다면") {
            every { productImageRepository.findByIds(any()) } returns listOf()

            Then("예외 발생") {
                val exception =
                    shouldThrow<BusinessLogicException> {
                        productImageService.upsert(upsertProductImageVOFixture)
                    }
                exception.code shouldBe BAD_REQUEST_ID
            }
        }
    }
})