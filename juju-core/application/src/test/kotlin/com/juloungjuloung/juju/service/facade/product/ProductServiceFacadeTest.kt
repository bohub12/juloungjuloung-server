package com.juloungjuloung.juju.service.facade.product

import com.juloungjuloung.juju.common.constant.TYPE_BRACELET
import com.juloungjuloung.juju.dto.product.command.SaveBraceletAdditionalCommand
import com.juloungjuloung.juju.dto.product.command.SaveProductCommand
import com.juloungjuloung.juju.s3.AwsS3Service
import com.juloungjuloung.juju.service.factory.ProductServiceFactory
import com.juloungjuloung.juju.service.product.impl.BraceletService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductServiceFacadeTest : BehaviorSpec({
    val productServiceFactory = mockk<ProductServiceFactory>()
    val awsS3Service = mockk<AwsS3Service>()
    val productServiceFacade = ProductServiceFacade(productServiceFactory, awsS3Service)

    Given("외부로부터의 String으로 Enum을 검색했을 때") {
        val invalidProductType = "ILLEGAL_ARGUMENT"
        val invalidSaveCommand = SaveProductCommand(
            productType = invalidProductType,
            name = "상품이름1",
            price = 12000,
            weightByMilliGram = 1000,
            isDiamond = false,
            totalDiamondCaratX100 = null,
            additionalBraceletCommand = null,
            additionalNecklaceCommand = null
        )
        val braceletProductType = "BRACELET"
        val braceletSaveCommand = SaveProductCommand(
            productType = braceletProductType,
            name = "상품이름1",
            price = 12000,
            weightByMilliGram = 1000,
            isDiamond = false,
            totalDiamondCaratX100 = null,
            additionalBraceletCommand = SaveBraceletAdditionalCommand(100, 10),
            additionalNecklaceCommand = null
        )

        When("찾아지지 않는다면") {
            Then("Exception 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    productServiceFacade.readProducts(invalidProductType, 0, 10)
                }

                shouldThrow<IllegalArgumentException> {
                    productServiceFacade.saveProducts(invalidSaveCommand)
                }
            }
        }

        When("찾아진다면") {
            val braceletService = mockk<BraceletService>(relaxed = true)

            every { productServiceFactory.get(any()) } returns braceletService

            productServiceFacade.readProducts(TYPE_BRACELET, 0, 10)
            productServiceFacade.saveProducts(braceletSaveCommand)

            Then("정상적으로 Service에게 요청을 위임할 수 있다") {
                verify { braceletService.read(any(), any()) }
                verify { braceletService.save(any()) }
            }
        }
    }
})