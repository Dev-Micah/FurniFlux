package com.Micah.FurniFlux.controller

import com.Micah.FurniFlux.dto.ProductRequest
import com.Micah.FurniFlux.dto.ProductResponse
import com.Micah.FurniFlux.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1/products")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createProduct(
        @RequestPart("product" ) request: ProductRequest,
        @RequestPart("image", required = false) image: MultipartFile
    ): ResponseEntity<ProductResponse>{
        val response = productService.createProduct(request, image)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllProducts(
        @PageableDefault(size = 20) pageable: Pageable
    ): ResponseEntity<Page<ProductResponse>>{
        return ResponseEntity(productService.getAllProducts(pageable), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.getProductById(id))
    }
}