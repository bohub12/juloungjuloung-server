package com.juloungjuloung.juju.service.facade.product

import com.juloungjuloung.juju.dto.product.command.SaveProductCommand
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.service.factory.ProductServiceFactory
import com.juloungjuloung.juju.service.product.impl.RingService
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
            productServiceFacade.saveProducts(
                command = SaveProductCommand(
                    productType = ProductTypeEnum.RING,
                    name = "name1",
                    price = 12000,
                    weightByMilliGram = 1000,
                    isDiamond = false,
                    totalDiamondCaratX100 = null,
                    additionalBraceletCommand = null,
                    additionalNecklaceCommand = null
                )
            )

            Then("해당 서비스에게 요청을 위임한다") {
                verify { ringService.read(any(), any()) }
                verify { ringService.save(any()) }
            }
        }
    }
})