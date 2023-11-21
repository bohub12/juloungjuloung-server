package com.juloungjuloung.juju.service.product.impl

import com.juloungjuloung.juju.common.constant.ProductTypeEnum
import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.repository.product.NecklaceRepository
import com.juloungjuloung.juju.service.product.ProductService
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

    @Transactional
    override fun save(product: Product): Boolean {
        if (product is Necklace) {
            return necklaceRepository.save(product)
        }

        throw IllegalStateException()
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.NECKLACE
    }
}