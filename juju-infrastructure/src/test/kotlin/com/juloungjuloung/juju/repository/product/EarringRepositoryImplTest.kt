package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.earringFixture
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class EarringRepositoryImplTest : SharedMySQLTestContainer() {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var earringRepositoryImpl: EarringRepositoryImpl

    @BeforeEach
    fun setUp() {
        em.flush()
        em.clear()
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenEarring = earringFixture()
        val savedId = earringRepositoryImpl.save(givenEarring)

        // when
        val findEarring = earringRepositoryImpl.findById(savedId)

        // then
        findEarring.name shouldBe givenEarring.name
        findEarring.productCode shouldBe givenEarring.productCode
    }

    @Test
    fun `save_성공`() {
        // given
        val givenEarring = earringFixture()

        // when
        val savedId = earringRepositoryImpl.save(givenEarring)

        // then
        savedId.shouldNotBeNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = earringRepositoryImpl.save(earringFixture())
        val givenEarring = earringFixture(updatable = true, id = savedId)
        em.flush()
        em.clear()

        // when
        val updatedId = earringRepositoryImpl.update(givenEarring)
        val updatedEarring = earringRepositoryImpl.findById(updatedId)

        // then
        updatedId shouldBe savedId
        updatedEarring.name shouldBe givenEarring.name
        updatedEarring.price shouldBe givenEarring.price
        updatedEarring.weightByMilliGram shouldBe givenEarring.weightByMilliGram
        updatedEarring.thumbnailImage shouldBe givenEarring.thumbnailImage
        updatedEarring.isDiamond shouldBe givenEarring.isDiamond
        updatedEarring.totalDiamondCaratX100 shouldBe givenEarring.totalDiamondCaratX100
        updatedEarring.isDisplay shouldBe givenEarring.isDisplay
    }
}