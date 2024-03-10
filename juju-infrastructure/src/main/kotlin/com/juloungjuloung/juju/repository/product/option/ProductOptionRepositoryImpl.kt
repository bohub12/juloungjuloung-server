package com.juloungjuloung.juju.repository.product.option

import com.juloungjuloung.juju.domain.product.ProductOption
import com.juloungjuloung.juju.domain.product.repository.ProductOptionRepository
import com.juloungjuloung.juju.entity.product.ProductOptionEntity
import com.juloungjuloung.juju.entity.product.QProductOptionEntity.Companion.productOptionEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProductOptionRepositoryImpl(
    private val delegate: ProductOptionJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : ProductOptionRepository {
    override fun findByIds(productOptionIds: List<Long>): List<ProductOption> {
        return delegate.findAllById(productOptionIds).map { it.toDomain() }
    }

    override fun findAllByProductOptionCategoryIds(productOptionCategoryIds: List<Long>): List<ProductOption> {
        return delegate.findAllByProductOptionCategoryIdInAndDeletedFalse(productOptionCategoryIds)
            .map { it.toDomain() }
    }

    override fun saveAll(productOptions: List<ProductOption>): List<Long> {
        return delegate.saveAll(productOptions.map { ProductOptionEntity.of(it) }).map { it.id }
    }

    override fun updateAll(productOptions: List<ProductOption>): List<Long> {
        for (productOption in productOptions) {
            jpaQueryFactory.update(productOptionEntity)
                .set(productOptionEntity.name, productOption.name)
                .set(productOptionEntity.additionalPrice, productOption.additionalPrice)
                .where(productOptionEntity.id.eq(productOption.id))
                .execute()
        }

        return productOptions.map { it.id }
    }
}