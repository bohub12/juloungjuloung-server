package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.repository.impl.BraceletRepository
import com.juloungjuloung.juju.entity.product.impl.BraceletEntity
import com.juloungjuloung.juju.entity.product.impl.QBraceletEntity.Companion.braceletEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class BraceletRepositoryImpl(
    private val delegate: BraceletJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : BraceletRepository {
    override fun findById(id: Long): Bracelet {
        return delegate.findById(id).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Bracelet> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size)).stream()
            .map { it.toDomain() }
            .toList()
    }

    override fun save(bracelet: Bracelet): Long {
        return delegate.save(BraceletEntity.of(bracelet)).id
    }

    override fun update(bracelet: Bracelet): Long {
        jpaQueryFactory.update(braceletEntity)
            .set(braceletEntity.name, bracelet.name)
            .set(braceletEntity.price, bracelet.price)
            .set(braceletEntity.weightByMilliGram, bracelet.weightByMilliGram)
            .set(braceletEntity.thumbnailImage, bracelet.thumbnailImage)
            .set(braceletEntity.isDiamond, bracelet.isDiamond)
            .set(braceletEntity.totalDiamondCaratX100, bracelet.totalDiamondCaratX100)
            .set(braceletEntity.isDisplay, bracelet.isDisplay)
            .set(braceletEntity.maximumLength, bracelet.maximumLength)
            .set(braceletEntity.minimumLength, bracelet.minimumLength)
            .where(braceletEntity.id.eq(bracelet.id))
            .execute()

        return bracelet.id
    }
}