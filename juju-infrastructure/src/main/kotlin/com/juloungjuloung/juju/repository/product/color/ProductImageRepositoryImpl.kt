package com.juloungjuloung.juju.repository.product.color

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.ProductImages
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
    override fun findByProduct(product: Product): List<ProductImage> {
        return jpaQueryFactory.selectFrom(productImageEntity)
            .where(productImageEntity.product.id.eq(product.id))
            .fetch()
            .map { it.toDomain() }
    }

    override fun save(productImage: ProductImage): Long {
        return delegate.save(ProductImageEntity.of(productImage)).id
    }

    override fun saveAll(productImages: ProductImages): List<Long> {
        return delegate.saveAll(productImages.productImages.map { ProductImageEntity.of(it) })
            .map { it.id }
    }

    override fun delete(productImageId: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(productImageIds: List<Long>) {
        TODO("Not yet implemented")
    }
}