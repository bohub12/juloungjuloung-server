package com.juloungjuloung.juju.application.facade.product

import com.juloungjuloung.juju.application.factory.ProductServiceFactory
import com.juloungjuloung.juju.domain.product.service.impl.RingService
import com.juloungjuloung.juju.domain.saveRingVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductServiceFacadeTest : BehaviorSpec({
    val productServiceFactory = mockk<ProductServiceFactory>()

    val productServiceFacade = ProductServiceFacade(productServiceFactory)

    Given("컨트롤러부터의 요청이 있을 때") {
        When("팩토리에서 서비스를 조회해") {
            val ringService = mockk<RingService>(relaxed = true)

            every { productServiceFactory.get(any()) } returns ringService

            productServiceFacade.readProducts(ProductTypeEnum.RING, 0, 10)
            productServiceFacade.saveProduct(saveRingVO())

            Then("해당 서비스에게 요청을 위임한다") {
                verify { ringService.read(any(), any()) }
                verify { ringService.save(any()) }
            }
        }
    }
})