package com.juloungjuloung.juju.application.factory

import com.juloungjuloung.juju.domain.product.repository.impl.BraceletRepository
import com.juloungjuloung.juju.domain.product.repository.impl.EarringRepository
import com.juloungjuloung.juju.domain.product.repository.impl.NecklaceRepository
import com.juloungjuloung.juju.domain.product.repository.impl.RingRepository
import com.juloungjuloung.juju.domain.product.service.impl.BraceletService
import com.juloungjuloung.juju.domain.product.service.impl.EarringService
import com.juloungjuloung.juju.domain.product.service.impl.NecklaceService
import com.juloungjuloung.juju.domain.product.service.impl.RingService
import com.juloungjuloung.juju.enums.ProductTypeEnum
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.mockk

class ProductServiceFactoryTest : BehaviorSpec({
    Given("Product Service들이 팩토리에 등록됐을 때") {
        val productServiceFactory = ProductServiceFactory(
            listOf(
                BraceletService(mockk<BraceletRepository>()),
                EarringService(mockk<EarringRepository>()),
                NecklaceService(mockk<NecklaceRepository>()),
                RingService(mockk<RingRepository>())
            )
        )

        When("정상적인 파라미터(enum)로 Service를 조회하면") {
            Then("정상적으로 리턴한다") {
                productServiceFactory.get(ProductTypeEnum.BRACELET).shouldBeTypeOf<BraceletService>()
                productServiceFactory.get(ProductTypeEnum.EARRING).shouldBeTypeOf<EarringService>()
                productServiceFactory.get(ProductTypeEnum.NECKLACE).shouldBeTypeOf<NecklaceService>()
                productServiceFactory.get(ProductTypeEnum.RING).shouldBeTypeOf<RingService>()
            }
        }
    }

    Given("Product Service 들이 등록되지 않았을 때") {
        val productServiceFactory = ProductServiceFactory(listOf())

        When("ServiceFactory 에 없는 타입으로 조회하면") {
            Then("exception 발생한다") {
                shouldThrowExactly<IllegalStateException> { productServiceFactory.get(ProductTypeEnum.BRACELET) }
            }
        }
    }
})