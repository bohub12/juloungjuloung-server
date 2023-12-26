package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class BraceletRepositoryImplTest : SharedMySQLTestContainer() {
    @Autowired
    lateinit var fixtureMonkey: FixtureMonkey

    @Autowired
    lateinit var braceletRepositoryImpl: BraceletRepositoryImpl

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