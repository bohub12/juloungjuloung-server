package com.juloungjuloung.juju.repository.product.material

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.domain.productmaterial.productMaterialCollectionFixture
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K18
import com.juloungjuloung.juju.enums.ProductMaterialEnum.K22
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(ProductMaterialRepositoryImpl::class)
class ProductMaterialRepositoryImplTest {

    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var productMaterialRepositoryImpl: ProductMaterialRepositoryImpl

    @AfterEach
    fun clearPersistenceContext() {
        em.flush()
        em.clear()
    }

    @Test
    fun `findByProduct_성공`() {
        // given
        val productId = 1L
        val savedProductMaterialIds = productMaterialRepositoryImpl.saveAll(
            productMaterialCollectionFixture(
                productId = productId,
                materials = listOf(K18, K22)
            )
        )

        // when
        val findProductMaterials = productMaterialRepositoryImpl.findByProduct(productId)

        // then
        findProductMaterials.map { it.id } shouldContainExactlyInAnyOrder savedProductMaterialIds
    }

    @Test
    fun `saveAll_성공`() {
        // given
        val productId = 1L
        val productMaterials = productMaterialCollectionFixture(productId = productId, materials = listOf(K18, K22))

        // when
        val savedProductMaterialIds = productMaterialRepositoryImpl.saveAll(productMaterials)

        // then
        val savedProductMaterials = productMaterialRepositoryImpl.findByProduct(productId)
        savedProductMaterials.size shouldBe productMaterials.size
        savedProductMaterials.map { it.id } shouldContainExactlyInAnyOrder savedProductMaterialIds
    }

    @Test
    fun `updateAll_성공`() {
        // given
        val productId = 1L
        val savedProductMaterialIds = productMaterialRepositoryImpl.saveAll(
            productMaterialCollectionFixture(
                productId = productId,
                materials = listOf(K18, K22)
            )
        )

        // when
        val additionalPriceForUpdate = 10000
        val productMaterialsForUpdate = productMaterialCollectionFixture(
            ids = savedProductMaterialIds,
            additionalPrices = List(savedProductMaterialIds.size) { additionalPriceForUpdate }
        )
        productMaterialRepositoryImpl.updateAll(productMaterialsForUpdate)

        em.flush()
        em.clear()

        // then
        val updatedProductMaterials = productMaterialRepositoryImpl.findByProduct(productId)
        updatedProductMaterials.forEach { it.additionalPrice shouldBe additionalPriceForUpdate }
    }

    @Test
    fun `deleteAll_성공`() {
        // given
        val productId = 1L
        val savedProductMaterialIds = productMaterialRepositoryImpl.saveAll(
            productMaterialCollectionFixture(
                productId = productId,
                materials = listOf(K18, K22)
            )
        )

        // when
        productMaterialRepositoryImpl.deleteAll(savedProductMaterialIds)

        // then
        val findProductMaterials = productMaterialRepositoryImpl.findByIds(savedProductMaterialIds)
        findProductMaterials.shouldBeEmpty()
    }
}