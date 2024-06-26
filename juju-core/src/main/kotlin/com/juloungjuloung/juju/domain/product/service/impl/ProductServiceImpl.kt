package com.juloungjuloung.juju.domain.product.service.impl

import com.juloungjuloung.juju.domain.product.Product
import com.juloungjuloung.juju.domain.product.repository.ProductRepository
import com.juloungjuloung.juju.domain.product.service.ProductService
import com.juloungjuloung.juju.domain.product.vo.SaveProductVO
import com.juloungjuloung.juju.domain.product.vo.UpdateProductVO
import com.juloungjuloung.juju.enums.ProductTypeEnum
import com.juloungjuloung.juju.response.ApiResponseCode.PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL
import com.juloungjuloung.juju.utils.require
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {
    override fun read(
        page: Int,
        size: Int
    ): List<Product> {
        return productRepository.findAllByOrderByCreatedAt(page, size)
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

    fun changeThumbnailImage(
        productId: Long,
        thumbnailImageUrl: String?
    ) {
        val product = readById(productId)

        if (thumbnailImageUrl.isNullOrBlank()) {
            require(!product.isDisplay, PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL)

            product.deleteThumbnailImage()
        } else {
            product.changeThumbnailImage(thumbnailImageUrl)
        }

        productRepository.changeThumbnailImage(product)
    }
}