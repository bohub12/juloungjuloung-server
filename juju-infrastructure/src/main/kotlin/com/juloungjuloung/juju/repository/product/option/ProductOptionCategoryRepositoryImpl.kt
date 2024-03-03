package com.juloungjuloung.juju.repository.product.option

import com.juloungjuloung.juju.domain.product.ProductOptionCategory
import com.juloungjuloung.juju.domain.product.repository.ProductOptionCategoryRepository
import com.juloungjuloung.juju.entity.product.ProductOptionCategoryEntity
import com.juloungjuloung.juju.entity.product.QProductOptionCategoryEntity.Companion.productOptionCategoryEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode.BAD_REQUEST_ID
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProductOptionCategoryRepositoryImpl(
    private val delegate: ProductOptionCategoryJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : ProductOptionCategoryRepository {
    override fun findById(productOptionCategoryId: Long): ProductOptionCategory {
        return delegate.findById(productOptionCategoryId).orElseThrow { BusinessLogicException(BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun save(productOptionCategory: ProductOptionCategory): Long {
        return delegate.save(ProductOptionCategoryEntity.of(productOptionCategory)).id
    }

    override fun update(productOptionCategory: ProductOptionCategory): Long {
        jpaQueryFactory.update(productOptionCategoryEntity)
            .set(productOptionCategoryEntity.name, productOptionCategory.name)
            .where(productOptionCategoryEntity.id.eq(productOptionCategory.id))
            .execute()

        return productOptionCategory.id
    }
}