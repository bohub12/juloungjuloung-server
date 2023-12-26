package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.Product
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryImplTest {
    @Autowired
    lateinit var fixtureMonkey: FixtureMonkey

    @Autowired
    lateinit var productRepositoryImpl: ProductRepositoryImpl

    @Test
    fun `save_성공`() {
        // given
        val givenProduct = fixtureMonkey.giveMeBuilder<Product>().sample()

        // when
        val savedId = productRepositoryImpl.save(givenProduct)

        // then
        assertThat(savedId).isNotNull()
    }

    @Test
    fun `findById_성공`() {
        // given
        val givenProduct = fixtureMonkey.giveMeBuilder<Product>().sample()
        val savedId = productRepositoryImpl.save(givenProduct)

        // when
        val findProduct = productRepositoryImpl.findById(savedId)

        // then
        findProduct.name shouldBe givenProduct.name
        findProduct.productCode shouldBe givenProduct.productCode
    }
}