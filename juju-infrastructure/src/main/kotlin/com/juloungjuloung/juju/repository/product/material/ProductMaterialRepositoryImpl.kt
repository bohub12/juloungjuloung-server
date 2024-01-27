package com.juloungjuloung.juju.repository.product.material

import com.juloungjuloung.juju.domain.product.ProductMaterial
import com.juloungjuloung.juju.domain.product.repository.ProductMaterialRepository
import com.juloungjuloung.juju.entity.product.ProductMaterialEntity
import com.juloungjuloung.juju.entity.product.QProductMaterialEntity.Companion.productMaterialEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProductMaterialRepositoryImpl(
    val delegate: ProductMaterialJpaRepository,
    val jpaQueryFactory: JPAQueryFactory
) : ProductMaterialRepository {
    override fun findByIds(productMaterialIds: List<Long>): List<ProductMaterial> {
        return delegate.findAllByIdInAndDeletedFalse(productMaterialIds)
            .map { it.toDomain() }
    }

    override fun findByProduct(productId: Long): List<ProductMaterial> {
        return delegate.findAllByProductIdAndDeletedFalse(productId)
            .map { it.toDomain() }
    }

    override fun saveAll(productMaterials: List<ProductMaterial>): List<Long> {
        return delegate.saveAll(productMaterials.map { ProductMaterialEntity.of(it) })
            .map { it.id }
    }

    override fun deleteAll(productMaterialIds: List<Long>) {
        jpaQueryFactory.update(productMaterialEntity)
            .set(productMaterialEntity.deleted, true)
            .where(productMaterialEntity.id.`in`(productMaterialIds))
            .execute()
    }
}