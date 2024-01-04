package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.domain.product.repository.impl.RingRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.enums.ProductTypeEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RingService(
    private val ringRepository: RingRepository
) : ProductService {

    override fun read(page: Int, size: Int): List<Product> {
        return ringRepository.findAllByOrderByCreatedAt(page, size)
    }

    override fun readById(id: Long): Product {
        return ringRepository.findById(id)
    }

    @Transactional
    override fun save(product: Product): Long {
        return ringRepository.save(product as Ring)
    }

    @Transactional
    override fun update(product: Product): Long {
        return ringRepository.update(product as Ring)
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.RING
    }
}