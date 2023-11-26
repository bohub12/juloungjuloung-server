package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.entity.product.impl.BraceletEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.querydsl.jpa.impl.JPAQueryFactory
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import java.util.*

class BraceletRepositoryImplTest : BehaviorSpec({
    Given("findById 호출됐을 때") {
        val braceletJpaRepository = mockk<BraceletJpaRepository>()
        val jpaQueryFactory = mockk<JPAQueryFactory>()
        val braceletRepositoryImpl = BraceletRepositoryImpl(braceletJpaRepository, jpaQueryFactory)

        When("해당 ID를 가지는 row가 없다면") {
            every { braceletJpaRepository.findById(any()) } returns Optional.empty()

            Then("Exception이 발생한다") {
                shouldThrow<BusinessLogicException> {
                    braceletRepositoryImpl.findById(1L)
                }
            }
        }

        When("해당 ID를 가지는 row가 있다면") {
            every { braceletJpaRepository.findById(any()) } returns Optional.of(
                BraceletEntity(
                    name = "name1",
                    productCode = "aaa",
                    price = 1000,
                    weightByMilliGram = 100,
                    thumbnailImage = null,
                    isDiamond = false,
                    totalDiamondCaratX100 = null,
                    isActive = true,
                    maximumLength = 100,
                    minimumLength = 10
                )
            )

            Then("정상 작동") {
                shouldNotThrow<BusinessLogicException> {
                    braceletRepositoryImpl.findById(1L)
                }
            }
        }
    }
})