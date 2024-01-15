package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk

class ProductImageServiceTest : BehaviorSpec({

    val productImageRepository = mockk<ProductImageRepository>()
    val productImageService = ProductImageService(productImageRepository)

    Given("상품 이미지들을 저장할 때") {
        When("저장되어 있는 이미지가 없고") {
            every { productImageRepository.findByProduct(any()) } returns listOf()
            every { productImageRepository.saveAll(any()) } returns listOf()

            Then("새로운 요청이 문제가 없다면 정상 실행된다") {
                val productImagesForSave = productImageCollectionFixture()

                shouldNotThrow<Exception> {
                    productImageService.saveAll(productImagesForSave)
                }
            }
        }

        When("저장되어 있는 이미지가 있고") {
            every { productImageRepository.findByProduct(any()) } returns productImageCollectionFixture()

            Then("새로운 요청 중 기본 이미지가 있다면 예외가 발생한다") {
            }

            Then("새로운 요청과 저장된 이미지들이 최대 개수를 넘기면 예외가 발생한다") {
            }
        }
    }
})