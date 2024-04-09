package com.juloungjuloung.juju.repository.product.option

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.domain.product.ProductOptionCategory
import com.juloungjuloung.juju.domain.productoption.productOptionCategoryFixture
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RepositoryIntegrationTest(ProductOptionCategoryRepositoryImpl::class)
class ProductOptionCategoryRepositoryImplTest {
    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var productOptionCategoryRepositoryImpl: ProductOptionCategoryRepositoryImpl

    @Test
    fun `save_성공`() {
        // given
        val productOptionCategory = productOptionCategoryFixture()

        // when
        val productOptionCategoryId = productOptionCategoryRepositoryImpl.save(productOptionCategory)

        // then
        val savedProductOptionCategory = productOptionCategoryRepositoryImpl.findById(productOptionCategoryId)
        productOptionCategoryId shouldBe savedProductOptionCategory.id
    }

    @Test
    fun `findById_성공`() {
        // given
        val savedProductOptionCategoryId = productOptionCategoryRepositoryImpl.save(productOptionCategoryFixture())

        shouldNotThrow<Exception> { productOptionCategoryRepositoryImpl.findById(savedProductOptionCategoryId) }
    }

    @Test
    fun `update_성공`() {
        // given
        val productOptionCategoryId = productOptionCategoryRepositoryImpl.save(productOptionCategoryFixture())

        // when
        val updateProductOptionCategoryName = "Updated Option Category Name"
        productOptionCategoryRepositoryImpl.update(
            productOptionCategory =
            ProductOptionCategory(
                id = productOptionCategoryId,
                productId = 1L,
                name = updateProductOptionCategoryName
            )
        )

        em.flush()
        em.clear()

        // then
        val foundProductOptionCategory = productOptionCategoryRepositoryImpl.findById(productOptionCategoryId)
        foundProductOptionCategory.name shouldBe updateProductOptionCategoryName
    }
}