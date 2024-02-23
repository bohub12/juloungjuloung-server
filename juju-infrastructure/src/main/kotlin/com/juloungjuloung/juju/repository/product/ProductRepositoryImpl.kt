package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.entity.product.QProductEntity.Companion.productEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val delegate: ProductJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : ProductRepository {
    // TODO : 삭제 기능 추가될 때, 논리적으로 삭제되지 않은 로우만 조회하도록 수정
    override fun findById(productId: Long): Product {
        return delegate.findById(productId).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Product> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size))
            .map { it.toDomain() }
            .toList()
    }

    override fun changeThumbnailImage(product: Product): Long {
        jpaQueryFactory.update(productEntity)
            .set(productEntity.thumbnailImage, product.thumbnailImage)
            .where(productEntity.id.eq(product.id))
            .execute()

        return product.id
    }

    override fun count(): Long {
        return delegate.count()
    }
}