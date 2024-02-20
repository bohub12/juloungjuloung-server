package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.productFixture
import com.juloungjuloung.juju.domain.product.ringFixture
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(ProductRepositoryImpl::class, RingRepositoryImpl::class)
class ProductRepositoryImplTest : SharedMySQLTestContainer() {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var ringRepositoryImpl: RingRepositoryImpl

    @Autowired
    lateinit var productRepositoryImpl: ProductRepositoryImpl

    @AfterEach
    fun clearPersistenceContext() {
        em.flush()
        em.clear()
    }

    @Test
    fun `changeThumbnailImage_성공`() {
        // given
        val ring = ringFixture()
        val savedProductId = ringRepositoryImpl.save(ring)

        em.flush()
        em.clear()

        // when
        val updatedProduct = productFixture(id = savedProductId, isDisplay = true)
        productRepositoryImpl.changeThumbnailImage(updatedProduct)

        em.flush()
        em.clear()

        // then
        val findProduct = productRepositoryImpl.findById(savedProductId)
        println("find : " + findProduct.thumbnailImage)
        updatedProduct.thumbnailImage shouldBe findProduct.thumbnailImage
    }
}