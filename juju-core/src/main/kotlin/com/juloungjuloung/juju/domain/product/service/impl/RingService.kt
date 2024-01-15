package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Ring
import com.juloungjuloung.juju.domain.product.repository.impl.RingRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
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
    override fun save(saveProductVO: SaveProductVO): Long {
        return ringRepository.save(saveProductVO.toDomain() as Ring)
    }

    @Transactional
    override fun update(updateProductVO: UpdateProductVO): Long {
        val findProduct = ringRepository.findById(updateProductVO.id)
        findProduct.update(updateProductVO)

        return ringRepository.update(findProduct)
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.RING
    }
}