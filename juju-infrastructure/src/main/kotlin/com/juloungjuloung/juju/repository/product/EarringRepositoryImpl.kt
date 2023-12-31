package com.juloungjuloung.juju.repository.product

import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.repository.EarringRepository
import com.juloungjuloung.juju.entity.product.impl.EarringEntity
import com.juloungjuloung.juju.entity.product.impl.QEarringEntity.Companion.earringEntity
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class EarringRepositoryImpl(
    private val delegate: EarringJpaRepository,
    private val jpaQueryFactory: JPAQueryFactory
) : EarringRepository {
    override fun findById(id: Long): Earring {
        return delegate.findById(id).orElseThrow { BusinessLogicException(ApiResponseCode.BAD_REQUEST_ID) }
            .toDomain()
    }

    override fun findAllByOrderByCreatedAt(page: Int, size: Int): List<Earring> {
        return delegate.findAllByOrderByCreatedAt(PageRequest.of(page, size)).stream()
            .map { it.toDomain() }
            .toList()
    }

    override fun save(earring: Earring): Long {
        return delegate.save(EarringEntity.of(earring)).id
    }

    override fun update(earring: Earring): Long {
        jpaQueryFactory.update(earringEntity)
            .set(earringEntity.name, earring.name)
            .set(earringEntity.price, earring.price)
            .set(earringEntity.weightByMilliGram, earring.weightByMilliGram)
            .set(earringEntity.thumbnailImage, earring.thumbnailImage)
            .set(earringEntity.isDiamond, earring.isDiamond)
            .set(earringEntity.totalDiamondCaratX100, earring.totalDiamondCaratX100)
            .set(earringEntity.isDisplay, earring.isDisplay)
            .where(earringEntity.id.eq(earring.id))
            .execute()

        return earring.id
    }
}