package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.repository.impl.NecklaceRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.enums.ProductTypeEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NecklaceService(
    private val necklaceRepository: NecklaceRepository
) : ProductService {
    override fun read(page: Int, size: Int): List<Product> {
        return necklaceRepository.findAllByOrderByCreatedAt(page, size)
    }

    override fun readById(id: Long): Product {
        return necklaceRepository.findById(id)
    }

    @Transactional
    override fun save(product: Product): Long {
        return necklaceRepository.save(product as Necklace)
    }

    @Transactional
    override fun update(product: Product): Long {
        return necklaceRepository.update(product as Necklace)
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.NECKLACE
    }
}