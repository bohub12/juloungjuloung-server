package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.braceletFixture
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(BraceletRepositoryImpl::class)
class BraceletRepositoryImplTest : SharedMySQLTestContainer() {
    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var braceletRepositoryImpl: BraceletRepositoryImpl

    @AfterEach
    fun clearPersistenceContext() {
        em.flush()
        em.clear()
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenBracelet = braceletFixture()
        val savedId = braceletRepositoryImpl.save(givenBracelet)

        // when
        val findBracelet = braceletRepositoryImpl.findById(savedId)

        // then
        findBracelet.name shouldBe givenBracelet.name
        findBracelet.productCode shouldBe givenBracelet.productCode
    }

    @Test
    fun `save_성공`() {
        // given
        val givenBracelet = braceletFixture()

        // when
        val savedId = braceletRepositoryImpl.save(givenBracelet)

        // then
        savedId.shouldNotBeNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = braceletRepositoryImpl.save(braceletFixture())
        val givenBracelet = braceletFixture(updatable = true, id = savedId)
        em.flush()
        em.clear()

        // when
        val updatedId = braceletRepositoryImpl.update(givenBracelet)
        val updatedBracelet = braceletRepositoryImpl.findById(updatedId)

        // then
        updatedId shouldBe savedId
        updatedBracelet.name shouldBe givenBracelet.name
        updatedBracelet.price shouldBe givenBracelet.price
        updatedBracelet.weightByMilliGram shouldBe givenBracelet.weightByMilliGram
        updatedBracelet.thumbnailImage shouldBe givenBracelet.thumbnailImage
        updatedBracelet.isDiamond shouldBe givenBracelet.isDiamond
        updatedBracelet.totalDiamondCaratX100 shouldBe givenBracelet.totalDiamondCaratX100
        updatedBracelet.isDisplay shouldBe givenBracelet.isDisplay
        updatedBracelet.maximumLength shouldBe givenBracelet.maximumLength
        updatedBracelet.minimumLength shouldBe givenBracelet.minimumLength
    }
}