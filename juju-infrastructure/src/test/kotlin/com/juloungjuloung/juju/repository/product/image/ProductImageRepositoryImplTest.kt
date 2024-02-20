package com.juloungjuloung.juju.repository.product.image

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.changeThumbnail
import com.juloungjuloung.juju.domain.product.containsThumbnail
import com.juloungjuloung.juju.domain.product.getThumbnail
import com.juloungjuloung.juju.domain.productimage.productImageCollectionFixture
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(ProductImageRepositoryImpl::class)
class ProductImageRepositoryImplTest : SharedMySQLTestContainer() {
    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var productImageRepositoryImpl: ProductImageRepositoryImpl

    @AfterEach
    fun clearPersistenceContext() {
        em.flush()
        em.clear()
    }

    @Test
    fun `findByProduct_성공`() {
        // given
        val thumbnailImageId = 1L
        val notThumbnailImageIds = listOf(2L, 3L)
        val productId = 1L

        val productImages = productImageCollectionFixture(
            thumbnailId = thumbnailImageId,
            notThumbnailIds = notThumbnailImageIds,
            productId = productId
        )

        productImageRepositoryImpl.saveAll(productImages)

        // when
        val findProductImages = productImageRepositoryImpl.findByProduct(productId = productId)

        // then
        findProductImages.size shouldBe notThumbnailImageIds.size + 1
        findProductImages.containsThumbnail() shouldBe true
    }

    @Test
    fun `findByIds_성공`() {
        // given
        val savedProductImageIds = productImageRepositoryImpl.saveAll(productImageCollectionFixture())

        // when
        val findProductImages = productImageRepositoryImpl.findByIds(savedProductImageIds)

        // then
        findProductImages.size shouldBe savedProductImageIds.size
    }

    @Test
    fun `saveAll_성공`() {
        // given
        val productImages = productImageCollectionFixture()

        // when
        val savedImageIds = productImageRepositoryImpl.saveAll(productImages)

        // then
        savedImageIds.size shouldBe productImages.size
    }

    @Test
    fun `deleteAll_성공`() {
        // given
        val thumbnailImageId = 1L
        val notThumbnailImageIds = listOf(2L, 3L)
        val productImages = productImageCollectionFixture(
            thumbnailId = thumbnailImageId,
            notThumbnailIds = notThumbnailImageIds
        )

        productImageRepositoryImpl.saveAll(productImages)

        // when
        productImageRepositoryImpl.deleteAll(notThumbnailImageIds)
        val emptyProductImages = productImageRepositoryImpl.findByIds(notThumbnailImageIds)

        // then
        emptyProductImages.size shouldBe 0
    }

    @Test
    fun `changeThumbnail_성공`() {
        // given
        val savedProductImageIds = productImageRepositoryImpl.saveAll(productImageCollectionFixture())
        val productImages = productImageRepositoryImpl.findByIds(savedProductImageIds)

        em.flush()
        em.clear()

        // when
        val newThumbnailImageId = productImages.last().id
        productImages.changeThumbnail(newThumbnailImageId)
        productImageRepositoryImpl.changeThumbnail(productImages)

        em.flush()
        em.clear()

        // then
        val findProductImages = productImageRepositoryImpl.findByIds(savedProductImageIds)
        findProductImages.getThumbnail().id shouldBe newThumbnailImageId
    }
}