package com.Micah.FurniFlux.service

import com.Micah.FurniFlux.dto.ProductRequest
import com.Micah.FurniFlux.dto.ProductResponse
import com.Micah.FurniFlux.exception.ResourceNotFoundException
import com.Micah.FurniFlux.model.Product
import com.Micah.FurniFlux.repository.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val cloudinaryService: CloudinaryService
){

    fun createProduct(request: ProductRequest, imageFile: MultipartFile): ProductResponse {

        val imageUrl = imageFile.let { cloudinaryService.uploadImage(it) }

        val product = Product(
            name = request.name.trim(),
            description = request.description.trim(),
            price = request.price,
            imageUrl = imageUrl,
            stockQuantity = request.stockQuantity,

        )

        return mapToResponse(productRepository.save(product))
    }

    fun getAllProducts(pageable: Pageable): Page<ProductResponse> {
        return productRepository.findAll(pageable).map { mapToResponse(it) }
    }

    fun getProductById(id: Long): ProductResponse {
        val product = productRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Product with ID $id does not exist.") }
        return mapToResponse(product)
    }

    private fun mapToResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id!!,
            name = product.name,
            description = product.description,
            price = product.price,
            stockQuantity = product.stockQuantity,
            imageUrl = product.imageUrl,
            createdAt = product.createdAt.toString()
        )
    }

}