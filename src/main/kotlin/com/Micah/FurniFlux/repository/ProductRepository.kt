package com.Micah.FurniFlux.repository

import com.Micah.FurniFlux.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Product>
}