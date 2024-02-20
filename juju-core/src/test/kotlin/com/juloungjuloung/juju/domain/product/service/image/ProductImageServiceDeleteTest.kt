package com.juloungjuloung.juju.domain.product.service.image

import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.productimage.productImageFixture
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductImageServiceDeleteTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productImageRepository = mockk<ProductImageRepository>(relaxed = true)
    val productImageService = ProductImageService(productRepository, productImageRepository)

    Given("상품 이미지를 삭제하려 할 때") {
        val productImageIdsForDelete = listOf(2L, 3L)

        Then("정상요청이라면, 정상 실행") {
            every { productImageRepository.findByIds(productImageIdsForDelete) } returns productImageIdsForDelete
                .map { productImageFixture(isThumbnail = false, id = it) }

            productImageService.delete(productImageIdsForDelete)

            verify { productImageRepository.deleteAll(productImageIdsForDelete) }
        }

        Then("요청 ID가 DB에 없다면 예외 발생") {
            every { productImageRepository.findByIds(productImageIdsForDelete) } returns listOf()

            val exception = shouldThrow<BusinessLogicException> {
                productImageService.delete(productImageIdsForDelete)
            }
            exception.code shouldBe ApiResponseCode.BAD_REQUEST_ID
        }

        Then("삭제하려는 이미지가 기본 이미지라면 예외 발생") {
            every { productImageRepository.findByIds(productImageIdsForDelete) } returns productImageIdsForDelete
                .mapIndexed { index, id -> productImageFixture(isThumbnail = index == 0, id = id) }

            val exception = shouldThrow<BusinessLogicException> {
                productImageService.delete(productImageIdsForDelete)
            }
            exception.code shouldBe ApiResponseCode.PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL
        }
    }
})