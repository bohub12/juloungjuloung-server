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
    // TODO : 삭제 기능 추가될 때, 논리적으로 삭제되지 않은 로우만 조회하도록 수정
    override fun findById(id: Long): Bracelet {
        return delegate.findById(id).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    // TODO : 삭제 기능 추가될 때, 논리적으로 삭제되지 않은 로우만 조회하도록 수정
    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Bracelet> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size))
            .map { it.toDomain() }
            .toList()
    }

    // TODO : 삭제 기능 추가될 때, 논리적으로 삭제되지 않은 로우만 조회하도록 수정
    override fun count(): Long {
        return delegate.count()
    }

    override fun save(bracelet: Bracelet): Long {
        return delegate.save(BraceletEntity.of(bracelet)).id
    }

    override fun update(bracelet: Bracelet): Long {
        jpaQueryFactory.update(braceletEntity)
            .set(braceletEntity.name, bracelet.name)
            .set(braceletEntity.price, bracelet.price)
            .set(braceletEntity.weightByMilliGram, bracelet.weightByMilliGram)
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