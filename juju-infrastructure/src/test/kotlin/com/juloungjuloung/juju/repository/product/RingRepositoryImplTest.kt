package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.impl.Ring
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
class RingRepositoryImplTest : SharedMySQLTestContainer() {
    val fixtureMonkey: FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .build()

    @Autowired
    lateinit var ringJpaRepository: RingJpaRepository

    @Autowired
    lateinit var jpaQueryFactory: JPAQueryFactory
    lateinit var ringRepositoryImpl: RingRepositoryImpl

    @BeforeEach
    fun setup() {
        ringRepositoryImpl = RingRepositoryImpl(ringJpaRepository, jpaQueryFactory)
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenRing = fixtureMonkey.giveMeBuilder<Ring>().sample()
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
        val givenRing = fixtureMonkey.giveMeBuilder<Ring>().sample()

        // when
        val savedId = ringRepositoryImpl.save(givenRing)

        // then
        assertThat(savedId).isNotNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = ringRepositoryImpl.save(fixtureMonkey.giveMeBuilder<Ring>().sample())
        val updateRing = fixtureMonkey.giveMeBuilder<Ring>()
            .setExp(Ring::id, savedId)
            .sample()

        // when
        val updatedId = ringRepositoryImpl.update(updateRing)

        // then
        assertThat(updatedId).isNotNull()
    }
}