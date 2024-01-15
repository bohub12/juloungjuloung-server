package com.juloungjuloung.juju.repository.product.image

import com.juloungjuloung.juju.domain.product.ProductImage
import com.juloungjuloung.juju.domain.product.repository.ProductImageRepository
import com.juloungjuloung.juju.entity.product.ProductImageEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProductImageRepositoryImpl(
    private val delegate: ProductImageJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : ProductImageRepository {
    override fun findByProduct(productId: Long): List<ProductImage> {
        return delegate.findByProductId(productId = productId)
            .map { it.toDomain() }
    }

    override fun save(productImage: ProductImage): Long {
        return delegate.save(ProductImageEntity.of(productImage)).id
    }

    override fun saveAll(productImages: List<ProductImage>): List<Long> {
        return delegate.saveAll(productImages.map { ProductImageEntity.of(it) })
            .map { it.id }
    }

    override fun delete(productImageId: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(productImageIds: List<Long>) {
        TODO("Not yet implemented")
    }
}