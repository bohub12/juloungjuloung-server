package com.juloungjuloung.juju.repository.product.image

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.getNonThumbnails
import com.juloungjuloung.juju.domain.product.getThumbnail
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.entity.product.ProductImageEntity
import com.juloungjuloung.juju.entity.product.QProductImageEntity.Companion.productImageEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProductImageRepositoryImpl(
    private val delegate: ProductImageJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : ProductImageRepository {
    override fun findByProduct(productId: Long): List<ProductImage> {
        return delegate.findByProductIdAndDeletedFalse(productId = productId)
            .map { it.toDomain() }
    }

    override fun findByIds(productImageIds: List<Long>): List<ProductImage> {
        return delegate.findAllByIdInAndDeletedFalse(productImageIds)
            .map { it.toDomain() }
    }

    override fun saveAll(productImages: List<ProductImage>): List<Long> {
        return delegate.saveAll(productImages.map { ProductImageEntity.of(it) })
            .map { it.id }
    }

    override fun deleteAll(productImageIds: List<Long>) {
        jpaQueryFactory.update(productImageEntity)
            .set(productImageEntity.deleted, true)
            .where(productImageEntity.id.`in`(productImageIds))
            .execute()
    }

    override fun changeThumbnail(productImages: List<ProductImage>) {
        jpaQueryFactory.update(productImageEntity)
            .set(productImageEntity.isThumbnail, false)
            .where(productImageEntity.id.`in`(productImages.getNonThumbnails().map { it.id }))
            .execute()

        jpaQueryFactory.update(productImageEntity)
            .set(productImageEntity.isThumbnail, true)
            .where(productImageEntity.id.eq(productImages.getThumbnail().id))
            .execute()
    }
}