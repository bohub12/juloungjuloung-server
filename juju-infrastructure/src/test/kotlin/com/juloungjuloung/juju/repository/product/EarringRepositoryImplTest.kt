package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.impl.Earring
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
class EarringRepositoryImplTest : SharedMySQLTestContainer() {
    @Autowired
    lateinit var fixtureMonkey: FixtureMonkey

    @Autowired
    lateinit var earringRepositoryImpl: EarringRepositoryImpl

    @Test
    fun `findById_성공`() {
        // given
        val givenEarring = fixtureMonkey.giveMeBuilder<Earring>().sample()
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
        val givenEarring = fixtureMonkey.giveMeBuilder<Earring>().sample()

        // when
        val savedId = earringRepositoryImpl.save(givenEarring)

        // then
        assertThat(savedId).isNotNull()
    }

    @Test
    fun `update_성공`() {
        // given
        val savedId = earringRepositoryImpl.save(fixtureMonkey.giveMeBuilder<Earring>().sample())
        val updateEarring = fixtureMonkey.giveMeBuilder<Earring>()
            .setExp(Earring::id, savedId)
            .sample()

        // when
        val updatedId = earringRepositoryImpl.update(updateEarring)

        // then
        assertThat(updatedId).isNotNull()
    }
}