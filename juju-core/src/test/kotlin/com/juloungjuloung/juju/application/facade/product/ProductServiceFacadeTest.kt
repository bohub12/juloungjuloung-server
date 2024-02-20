package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.application.factory.ProductServiceFactory
import com.juloungjuloung.juju.domain.product.saveProductVOFixture
import com.juloungjuloung.juju.domain.product.service.ProductImageService
import com.juloungjuloung.juju.domain.product.service.impl.RingService
import com.juloungjuloung.juju.enums.ProductTypeEnum.RING
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductServiceFacadeTest : BehaviorSpec({

    Given("상품 저장 요청에 썸네일이미지도 포함되어 있다면") {
        val productService = mockk<RingService>(relaxed = true)
        val productImageService = mockk<ProductImageService>(relaxed = true)
        every { productService.getProductType() } returns RING

        val productServiceFactory = ProductServiceFactory(listOf(productService))
        val productServiceFacade = ProductServiceFacade(productServiceFactory, productImageService)

        val saveProductVO = saveProductVOFixture(RING)

        When("상품 저장 시") {
            productServiceFacade.save(saveProductVO)

            Then("상품 이미지도 같이 저장한다") {
                verify { productImageService.saveAll(any()) }
                verify { productService.save(any()) }
            }
        }
    }
})