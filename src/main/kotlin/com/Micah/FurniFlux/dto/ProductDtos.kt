package com.Micah.FurniFlux.dto

import java.math.BigDecimal

data class ProductRequest(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stockQuantity: Int,
)

data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val imageUrl: String?,
    val createdAt: String,
)