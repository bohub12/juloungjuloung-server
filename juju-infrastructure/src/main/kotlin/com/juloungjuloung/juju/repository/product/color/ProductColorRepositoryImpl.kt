package com.juloungjuloung.juju.repository.product.color

import com.juloungjuloung.juju.domain.product.ProductColor
import com.juloungjuloung.juju.domain.product.repository.ProductColorRepository
import com.juloungjuloung.juju.entity.product.ProductColorEntity
import com.juloungjuloung.juju.entity.product.QProductColorEntity.Companion.productColorEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProductColorRepositoryImpl(
    private val delegate: ProductColorJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : ProductColorRepository {
    override fun findByIds(productColorIds: List<Long>): List<ProductColor> {
        return delegate.findAllByIdInAndDeletedFalse(productColorIds)
            .map { it.toDomain() }
    }

    override fun findByProduct(productId: Long): List<ProductColor> {
        return delegate.findAllByProductIdAndDeletedFalse(productId)
            .map { it.toDomain() }
    }

    override fun saveAll(productColors: List<ProductColor>): List<Long> {
        return delegate.saveAll(productColors.map { ProductColorEntity.of(it) })
            .map { it.id }
    }

    override fun deleteAll(productColorIds: List<Long>) {
        jpaQueryFactory.update(productColorEntity)
            .set(productColorEntity.deleted, true)
            .where(productColorEntity.id.`in`(productColorIds))
            .execute()
    }
}