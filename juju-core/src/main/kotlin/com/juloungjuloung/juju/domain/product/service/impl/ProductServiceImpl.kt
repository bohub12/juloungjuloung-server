package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {
    override fun read(page: Int, size: Int): List<Product> {
        TODO("Not yet implemented")
    }

    override fun readById(id: Long): Product {
        return productRepository.findById(id)
    }

    override fun count(): Long {
        return productRepository.count()
    }

    override fun save(saveProductVO: SaveProductVO): Long {
        TODO("Not yet implemented")
    }

    override fun update(updateProductVO: UpdateProductVO): Long {
        TODO("Not yet implemented")
    }

    override fun getProductType(): ProductTypeEnum {
        return ProductTypeEnum.BASE
    }
}