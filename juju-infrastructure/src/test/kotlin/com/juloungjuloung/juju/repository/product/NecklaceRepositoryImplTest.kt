package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.necklaceFixture
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(NecklaceRepositoryImpl::class)
class NecklaceRepositoryImplTest : SharedMySQLTestContainer() {
    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var necklaceRepositoryImpl: NecklaceRepositoryImpl

    @AfterEach
    fun clearPersistenceContext() {
        em.flush()
        em.clear()
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenNecklace = necklaceFixture()
        val savedId = necklaceRepositoryImpl.save(givenNecklace)

        // when
        val findNecklace = necklaceRepositoryImpl.findById(savedId)

        // then
        findNecklace.name shouldBe givenNecklace.name
        findNecklace.productCode shouldBe givenNecklace.productCode
    }

    @Test
    fun `save_성공`() {
        // given
        val givenNecklace = necklaceFixture()

        // when
        val savedId = necklaceRepositoryImpl.save(givenNecklace)

        // then
        savedId.shouldNotBeNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = necklaceRepositoryImpl.save(necklaceFixture())
        val givenNecklace = necklaceFixture(updatable = true, id = savedId)
        em.flush()
        em.clear()

        // when
        val updatedId = necklaceRepositoryImpl.update(givenNecklace)
        val updatedNecklace = necklaceRepositoryImpl.findById(updatedId)

        // then
        updatedId shouldBe savedId
        updatedNecklace.name shouldBe givenNecklace.name
        updatedNecklace.price shouldBe givenNecklace.price
        updatedNecklace.weightByMilliGram shouldBe givenNecklace.weightByMilliGram
        updatedNecklace.thumbnailImage shouldBe givenNecklace.thumbnailImage
        updatedNecklace.isDiamond shouldBe givenNecklace.isDiamond
        updatedNecklace.totalDiamondCaratX100 shouldBe givenNecklace.totalDiamondCaratX100
        updatedNecklace.isDisplay shouldBe givenNecklace.isDisplay
    }
}