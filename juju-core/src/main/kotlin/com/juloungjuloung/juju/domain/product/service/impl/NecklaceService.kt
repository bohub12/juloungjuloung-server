package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Necklace
import com.juloungjuloung.juju.domain.product.repository.impl.NecklaceRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
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

    override fun count(): Long {
        return necklaceRepository.count()
    }

    @Transactional
    override fun save(saveProductVO: SaveProductVO): Long {
        return necklaceRepository.save(saveProductVO.toDomain() as Necklace)
    }

    @Transactional
    override fun update(updateProductVO: UpdateProductVO): Long {
        val findProduct = necklaceRepository.findById(updateProductVO.id)
        findProduct.update(updateProductVO)

        return necklaceRepository.update(findProduct)
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.NECKLACE
    }
}