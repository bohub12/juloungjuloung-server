package com.juloungjuloung.juju.repository.product.option

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.domain.productoption.productOptionCollectionFixture
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(ProductOptionRepositoryImpl::class)
class ProductOptionRepositoryImplTest {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var productOptionRepositoryImpl: ProductOptionRepositoryImpl

    @Test
    fun `findByIds_성공`() {
        // given
        val productOptionIds = productOptionRepositoryImpl.saveAll(productOptionCollectionFixture())

        // when
        val savedProductOptions = productOptionRepositoryImpl.findByIds(productOptionIds)

        // then
        savedProductOptions.size shouldBe productOptionIds.size
    }

    @Test
    fun `saveAll_성공`() {
        // given, when
        val productOptionIds = productOptionRepositoryImpl.saveAll(productOptionCollectionFixture())

        // then
        val savedProductOptions = productOptionRepositoryImpl.findByIds(productOptionIds)

        savedProductOptions.map { it.id } shouldContainExactlyInAnyOrder productOptionIds
    }

    @Test
    fun `updateAll_성공`() {
        // given
        val productOptionIds = productOptionRepositoryImpl.saveAll(productOptionCollectionFixture())

        val additionalPriceForUpdate = 10000L
        val productOptionsForUpdate = productOptionCollectionFixture(ids = productOptionIds)

        productOptionsForUpdate.map { it.additionalPrice = additionalPriceForUpdate }

        // when
        productOptionRepositoryImpl.updateAll(productOptionsForUpdate)

        em.flush()
        em.clear()

        // then
        val updatedProductOptions = productOptionRepositoryImpl.findByIds(productOptionIds)

        updatedProductOptions.size shouldBe productOptionIds.size
        updatedProductOptions.map { it.additionalPrice } shouldContainExactlyInAnyOrder
            List(productOptionIds.size) { additionalPriceForUpdate }
    }
}