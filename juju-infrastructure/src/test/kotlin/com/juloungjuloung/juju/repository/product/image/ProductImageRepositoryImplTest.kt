package com.juloungjuloung.juju.repository.product.image

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.SharedMySQLTestContainer
import com.juloungjuloung.juju.domain.product.ProductImages
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

        val productImages =
            productImageCollectionFixture(
                thumbnailId = thumbnailImageId,
                notThumbnailIds = notThumbnailImageIds,
                productId = productId
            )

        productImageRepositoryImpl.saveAll(productImages)

        // when
        val findProductImages = ProductImages(productImageRepositoryImpl.findByProduct(productId = productId))

        // then
        findProductImages.getProductImageIds().size shouldBe notThumbnailImageIds.size + 1
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
    fun `updateAll_성공`() {
        // given
        val productImages = productImageCollectionFixture()

        // when
        val upsertImageIds = productImageRepositoryImpl.updateAll(productImages)

        // then
        upsertImageIds.size shouldBe productImages.size
    }

    @Test
    fun `deleteAll_성공`() {
        // given
        val thumbnailImageId = 1L
        val notThumbnailImageIds = listOf(2L, 3L)
        val productImages =
            productImageCollectionFixture(
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
}