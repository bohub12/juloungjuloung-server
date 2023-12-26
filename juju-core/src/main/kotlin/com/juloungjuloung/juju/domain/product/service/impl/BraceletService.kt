package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.repository.product.BraceletRepository
import com.juloungjuloung.juju.service.product.ProductService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BraceletService(
    private val braceletRepository: BraceletRepository
) : ProductService {

    override fun read(page: Int, size: Int): List<Product> {
        return braceletRepository.findAllByOrderByCreatedAt(page, size)
    }

    override fun readById(id: Long): Product {
        return braceletRepository.findById(id)
    }

    @Transactional
    override fun save(product: Product): Long {
        return braceletRepository.save(product as Bracelet)
    }

    @Transactional
    override fun update(product: Product): Long {
        return braceletRepository.update(product as Bracelet)
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.BRACELET
    }
}