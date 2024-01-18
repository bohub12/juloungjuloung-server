package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.ringFixture
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(RingRepositoryImpl::class)
class RingRepositoryImplTest : SharedMySQLTestContainer() {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var ringRepositoryImpl: RingRepositoryImpl

    @AfterEach
    fun clearPersistenceContext() {
        em.flush()
        em.clear()
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenRing = ringFixture()
        val savedId = ringRepositoryImpl.save(givenRing)

        // when
        val findRing = ringRepositoryImpl.findById(savedId)

        // then
        findRing.name shouldBe givenRing.name
        findRing.productCode shouldBe givenRing.productCode
    }

    @Test
    fun `save_성공`() {
        // given
        val givenRing = ringFixture()

        // when
        val savedId = ringRepositoryImpl.save(givenRing)

        // then
        savedId.shouldNotBeNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = ringRepositoryImpl.save(ringFixture())
        val givenRing = ringFixture(updatable = true, id = savedId)
        em.flush()
        em.clear()

        // when
        val updatedId = ringRepositoryImpl.update(givenRing)
        val updatedRing = ringRepositoryImpl.findById(updatedId)

        // then
        updatedId shouldBe savedId
        updatedRing.name shouldBe givenRing.name
        updatedRing.price shouldBe givenRing.price
        updatedRing.weightByMilliGram shouldBe givenRing.weightByMilliGram
        updatedRing.thumbnailImage shouldBe givenRing.thumbnailImage
        updatedRing.isDiamond shouldBe givenRing.isDiamond
        updatedRing.totalDiamondCaratX100 shouldBe givenRing.totalDiamondCaratX100
        updatedRing.isDisplay shouldBe givenRing.isDisplay
    }
}