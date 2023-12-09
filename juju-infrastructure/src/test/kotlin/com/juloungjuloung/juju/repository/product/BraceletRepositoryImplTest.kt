package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.impl.Bracelet
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
class BraceletRepositoryImplTest : SharedMySQLTestContainer() {
    val fixtureMonkey: FixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .build()

    @Autowired
    lateinit var braceletJpaRepository: BraceletJpaRepository

    @Autowired
    lateinit var jpaQueryFactory: JPAQueryFactory
    lateinit var braceletRepositoryImpl: BraceletRepositoryImpl

    @BeforeEach
    fun setup() {
        braceletRepositoryImpl = BraceletRepositoryImpl(braceletJpaRepository, jpaQueryFactory)
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenBracelet = fixtureMonkey.giveMeBuilder<Bracelet>().sample()
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
        val givenBracelet = fixtureMonkey.giveMeBuilder<Bracelet>().sample()

        // when
        val savedId = braceletRepositoryImpl.save(givenBracelet)

        // then
        assertThat(savedId).isNotNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = braceletRepositoryImpl.save(fixtureMonkey.giveMeBuilder<Bracelet>().sample())
        val updateBracelet = fixtureMonkey.giveMeBuilder<Bracelet>()
            .setExp(Bracelet::id, savedId)
            .sample()

        // when
        val updatedId = braceletRepositoryImpl.update(updateBracelet)

        // then
        assertThat(updatedId).isNotNull()
    }
}