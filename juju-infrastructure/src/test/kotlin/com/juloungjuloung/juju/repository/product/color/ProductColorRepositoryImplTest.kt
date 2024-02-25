package com.juloungjuloung.juju.repository.product.color

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.domain.productcolor.productColorCollectionFixture
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(ProductColorRepositoryImpl::class)
class ProductColorRepositoryImplTest {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var productColorRepositoryImpl: ProductColorRepositoryImpl

    @AfterEach
    fun clearPersistenceContext() {
        em.flush()
        em.clear()
    }

    @Test
    fun `findById_성공`() {
        // given
        val productId = 1L
        val productColorIds = productColorRepositoryImpl.saveAll(productColorCollectionFixture(productId = productId))

        // when
        val productColors = productColorRepositoryImpl.findByProduct(productId)

        // then
        productColors.size shouldBe productColorIds.size
        productColors.map { it.id } shouldContainExactlyInAnyOrder productColorIds
    }

    @Test
    fun `updateAll_성공`() {
        // given
        val productId = 1L
        val persistedProductColorIds =
            productColorRepositoryImpl.saveAll(productColorCollectionFixture(productId = productId))

        // when
        val updatedProductColors = productColorCollectionFixture(ids = persistedProductColorIds)
        productColorRepositoryImpl.updateAll(updatedProductColors)

        em.flush()
        em.clear()

        // then
        val findProductColors = productColorRepositoryImpl.findByProduct(productId)
        findProductColors.size shouldBe updatedProductColors.size
        findProductColors shouldContainExactly updatedProductColors
    }

    @Test
    fun `saveAll_성공`() {
        // given
        val productId = 1L
        val productColors = productColorCollectionFixture(productId = productId)

        // when
        val savedProductColorIds = productColorRepositoryImpl.saveAll(productColors)

        // then
        val findProductColors = productColorRepositoryImpl.findByProduct(productId)
        findProductColors.size shouldBe savedProductColorIds.size
        findProductColors.map { it.color } shouldContainExactlyInAnyOrder productColors.map { it.color }
        findProductColors.map { it.additionalPrice }
            .shouldContainExactlyInAnyOrder(productColors.map { it.additionalPrice })
    }

    @Test
    fun `deleteAll_성공`() {
        // given
        val productId = 1L
        val productColorIds = productColorRepositoryImpl.saveAll(productColorCollectionFixture(productId = productId))

        // when
        productColorRepositoryImpl.deleteAll(productColorIds)

        // then
        val findProductColors = productColorRepositoryImpl.findByIds(productColorIds)
        findProductColors.shouldBeEmpty()
    }
}