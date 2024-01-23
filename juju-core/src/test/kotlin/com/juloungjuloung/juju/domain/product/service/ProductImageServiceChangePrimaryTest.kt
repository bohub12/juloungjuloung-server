package com.juloungjuloung.juju.domain.product.service

import com.juloungjuloung.juju.domain.product.getPrimary
import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.productImage.productImageCollectionFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify

class ProductImageServiceChangePrimaryTest : BehaviorSpec({
    val productRepository = mockk<ProductRepository>()
    val productImageRepository = mockk<ProductImageRepository>()
    val productImageService = ProductImageService(productRepository, productImageRepository)

    Given("상품 이미지들이 저장되어 있고") {
        val productId = 1L
        val product = productFixture(id = productId)

        val primaryProductImageId = 1L
        val notPrimaryProductImageIds = listOf(2L, 3L)
        val productImages = productImageCollectionFixture(
            primaryId = primaryProductImageId,
            notPrimaryIds = notPrimaryProductImageIds,
            productId = productId
        )

        every { productRepository.findById(productId) } returns product
        every { productImageRepository.findByProduct(productId) } returns productImages
        every { productRepository.changePrimaryImage(any()) } returns productId
        every { productImageRepository.updatePrimary(any()) } just Runs

        When("기본이미지를 변경하면") {
            val newPrimaryProductImageId = 3L
            productImageService.changePrimary(productId, newPrimaryProductImageId)

            Then("상품 썸네일 이미지와 상품 기본 이미지가 변경된다") {
                productImages.getPrimary().id shouldBe newPrimaryProductImageId
                product.thumbnailImage shouldBe productImages.first { it.id == 3L }.imageUrl

                verify { productRepository.changePrimaryImage(any()) }
                verify { productImageRepository.updatePrimary(any()) }
            }
        }
    }
})