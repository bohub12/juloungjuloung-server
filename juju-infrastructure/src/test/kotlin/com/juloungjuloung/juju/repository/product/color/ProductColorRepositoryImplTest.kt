package com.juloungjuloung.juju.repository.product.color

import com.juloungjuloung.juju.RepositoryIntegrationTest
import com.juloungjuloung.juju.domain.productcolor.productColorCollectionFixture
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
    fun `saveAll_성공`() {
        // given
        val productId = 1L
        val productColors = productColorCollectionFixture(productId = productId)

        // when
        productColorRepositoryImpl.saveAll(productColors)

        // then
        // TODO : findByProduct 로 조회해서 저장되었는지 확인하는 로직 추가
    }
}