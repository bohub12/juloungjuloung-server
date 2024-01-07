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
    override fun findById(id: Long): Necklace {
        return delegate.findById(id).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Necklace> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size))
            .map { it.toDomain() }
            .toList()
    }

    override fun save(necklace: Necklace): Long {
        return delegate.save(NecklaceEntity.of(necklace)).id
    }

    override fun update(necklace: Necklace): Long {
        jpaQueryFactory.update(necklaceEntity)
            .set(necklaceEntity.name, necklace.name)
            .set(necklaceEntity.price, necklace.price)
            .set(necklaceEntity.weightByMilliGram, necklace.weightByMilliGram)
            .set(necklaceEntity.thumbnailImage, necklace.thumbnailImage)
            .set(necklaceEntity.isDiamond, necklace.isDiamond)
            .set(necklaceEntity.totalDiamondCaratX100, necklace.totalDiamondCaratX100)
            .set(necklaceEntity.isDisplay, necklace.isDisplay)
            .set(necklaceEntity.maximumLength, necklace.maximumLength)
            .set(necklaceEntity.minimumLength, necklace.minimumLength)
            .where(necklaceEntity.id.eq(necklace.id))
            .execute()

        return necklace.id
    }
}