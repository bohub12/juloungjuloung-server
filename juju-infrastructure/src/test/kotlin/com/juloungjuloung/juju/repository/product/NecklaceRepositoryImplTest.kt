package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import com.querydsl.jpa.impl.JPAQueryFactory
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class NecklaceRepositoryImplTest : SharedMySQLTestContainer() {
    val fixtureMonkey: FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .build()

    @Autowired
    lateinit var necklaceJpaRepository: NecklaceJpaRepository

    @Autowired
    lateinit var jpaQueryFactory: JPAQueryFactory
    lateinit var necklaceRepositoryImpl: NecklaceRepositoryImpl

    @BeforeEach
    fun setup() {
        necklaceRepositoryImpl = NecklaceRepositoryImpl(necklaceJpaRepository, jpaQueryFactory)
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenNecklace = fixtureMonkey.giveMeBuilder<Necklace>().sample()
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
        val givenNecklace = fixtureMonkey.giveMeBuilder<Necklace>().sample()

        // when
        val savedId = necklaceRepositoryImpl.save(givenNecklace)

        // then
        assertThat(savedId).isNotNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = necklaceRepositoryImpl.save(fixtureMonkey.giveMeBuilder<Necklace>().sample())
        val updateNecklace = fixtureMonkey.giveMeBuilder<Necklace>()
            .setExp(Necklace::id, savedId)
            .sample()

        // when
        val updatedId = necklaceRepositoryImpl.update(updateNecklace)

        // then
        assertThat(updatedId).isNotNull()
    }
}