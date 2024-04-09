package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.repository.impl.NecklaceRepository
import com.juloungjuloung.juju.entity.product.impl.NecklaceEntity
import com.juloungjuloung.juju.entity.product.impl.QNecklaceEntity.Companion.necklaceEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class NecklaceRepositoryImpl(
    private val delegate: NecklaceJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : NecklaceRepository {
    // TODO : 삭제 기능 추가될 때, 논리적으로 삭제되지 않은 로우만 조회하도록 수정
    override fun findById(id: Long): Necklace {
        return delegate.findById(id).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun findAllByOrderByCreatedAt(
        page: Int,
        size: Int
    ): List<Necklace> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size))
            .map { it.toDomain() }
            .toList()
    }

    override fun count(): Long {
        return delegate.count()
    }

    override fun save(necklace: Necklace): Long {
        return delegate.save(NecklaceEntity.of(necklace)).id
    }

    override fun update(necklace: Necklace): Long {
        jpaQueryFactory.update(necklaceEntity)
            .set(necklaceEntity.name, necklace.name)
            .set(necklaceEntity.price, necklace.price)
            .set(necklaceEntity.weightByMilliGram, necklace.weightByMilliGram)
            .set(necklaceEntity.isDiamond, necklace.isDiamond)
            .set(necklaceEntity.totalDiamondCaratX100, necklace.totalDiamondCaratX100)
            .set(necklaceEntity.isDisplay, necklace.isDisplay)
            .where(necklaceEntity.id.eq(necklace.id))
            .execute()

        return necklace.id
    }
}