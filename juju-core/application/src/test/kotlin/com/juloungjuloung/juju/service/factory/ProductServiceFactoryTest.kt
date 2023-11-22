package com.juloungjuloung.juju.service.factory

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.repository.product.BraceletRepository
import com.juloungjuloung.juju.repository.product.EarringRepository
import com.juloungjuloung.juju.repository.product.NecklaceRepository
import com.juloungjuloung.juju.repository.product.RingRepository
import com.juloungjuloung.juju.service.product.ProductService
import com.juloungjuloung.juju.service.product.impl.BraceletService
import com.juloungjuloung.juju.service.product.impl.EarringService
import com.juloungjuloung.juju.service.product.impl.NecklaceService
import com.juloungjuloung.juju.service.product.impl.RingService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.mockk

class ProductServiceFactoryTest : BehaviorSpec({

    val productServices: List<ProductService> = listOf(
        BraceletService(mockk<BraceletRepository>()),
        EarringService(mockk<EarringRepository>()),
        NecklaceService(mockk<NecklaceRepository>()),
        RingService(mockk<RingRepository>())
    )
    val productServiceFactory = ProductServiceFactory(productServices)

    Given("Product Service들이 팩토리에 등록됐을 때") {
        When("정상적인 파라미터(enum)로 Service를 조회하면") {
            Then("정상적으로 리턴한다") {
                productServiceFactory.get(ProductTypeEnum.BRACELET).shouldBeTypeOf<BraceletService>()
                productServiceFactory.get(ProductTypeEnum.EARRING).shouldBeTypeOf<EarringService>()
                productServiceFactory.get(ProductTypeEnum.NECKLACE).shouldBeTypeOf<NecklaceService>()
                productServiceFactory.get(ProductTypeEnum.RING).shouldBeTypeOf<RingService>()
            }
        }
    }
})