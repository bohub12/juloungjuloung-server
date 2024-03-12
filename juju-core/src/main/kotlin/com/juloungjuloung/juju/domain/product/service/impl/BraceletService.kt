package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.impl.Bracelet
import com.juloungjuloung.juju.domain.product.repository.impl.BraceletRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
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

    override fun count(): Long {
        return braceletRepository.count()
    }

    @Transactional
    override fun save(saveProductVO: SaveProductVO): Long {
        return braceletRepository.save(Bracelet.create(saveProductVO))
    }

    @Transactional
    override fun update(updateProductVO: UpdateProductVO): Long {
        val findProduct = braceletRepository.findById(updateProductVO.id)
        findProduct.update(updateProductVO)

        return braceletRepository.update(findProduct)
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.BRACELET
    }
}