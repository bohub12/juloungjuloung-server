package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.entity.product.QProductEntity.Companion.productEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val delegate: ProductJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : ProductRepository {
    override fun findById(productId: Long): Product {
        return delegate.findById(productId).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun changePrimaryImage(product: Product): Long {
        jpaQueryFactory.update(productEntity)
            .set(productEntity.thumbnailImage, product.thumbnailImage)
            .where(productEntity.id.eq(product.id))
            .execute()

        return product.id
    }
}