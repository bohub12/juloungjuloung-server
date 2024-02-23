package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.domain.product.repository.impl.RingRepository
import com.juloungjuloung.juju.entity.product.impl.QRingEntity.Companion.ringEntity
import com.juloungjuloung.juju.entity.product.impl.RingEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class RingRepositoryImpl(
    private val delegate: RingJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : RingRepository {
    // TODO : 삭제 기능 추가될 때, 논리적으로 삭제되지 않은 로우만 조회하도록 수정
    override fun findById(id: Long): Ring {
        return delegate.findById(id).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Ring> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size))
            .map { it.toDomain() }
            .toList()
    }

    override fun count(): Long {
        return delegate.count()
    }

    override fun save(ring: Ring): Long {
        return delegate.save(RingEntity.of(ring)).id
    }

    override fun update(ring: Ring): Long {
        jpaQueryFactory.update(ringEntity)
            .set(ringEntity.name, ring.name)
            .set(ringEntity.price, ring.price)
            .set(ringEntity.weightByMilliGram, ring.weightByMilliGram)
            .set(ringEntity.isDiamond, ring.isDiamond)
            .set(ringEntity.totalDiamondCaratX100, ring.totalDiamondCaratX100)
            .set(ringEntity.isDisplay, ring.isDisplay)
            .where(ringEntity.id.eq(ring.id))
            .execute()

        return ring.id
    }
}