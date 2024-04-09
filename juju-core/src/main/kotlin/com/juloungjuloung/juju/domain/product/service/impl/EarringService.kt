package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Earring
import com.juloungjuloung.juju.domain.product.repository.impl.EarringRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class EarringService(
    private val earringRepository: EarringRepository
) : ProductService {
    override fun read(
        page: Int,
        size: Int
    ): List<Product> {
        return earringRepository.findAllByOrderByCreatedAt(page, size)
    }

    override fun readById(id: Long): Product {
        return earringRepository.findById(id)
    }

    override fun count(): Long {
        return earringRepository.count()
    }

    @Transactional
    override fun save(saveProductVO: SaveProductVO): Long {
        return earringRepository.save(Earring.create(saveProductVO))
    }

    @Transactional
    override fun update(updateProductVO: UpdateProductVO): Long {
        val findProduct = earringRepository.findById(updateProductVO.id)
        findProduct.update(updateProductVO)

        return earringRepository.update(findProduct)
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.EARRING
    }
}