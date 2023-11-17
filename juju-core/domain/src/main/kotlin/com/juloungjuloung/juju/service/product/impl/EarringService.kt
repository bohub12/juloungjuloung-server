package com.juloungjuloung.juju.service.product.impl

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.repository.product.EarringRepository
import com.juloungjuloung.juju.service.product.ProductService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class EarringService(
    private val earringRepository: EarringRepository
) : ProductService {
    override fun read(page: Int, size: Int): List<Product> {
        return earringRepository.findAllByOrderByCreatedAt(page, size)
    }

    override fun save(products: List<Product>) {
        TODO("Not yet implemented")
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.EARRING
    }
}