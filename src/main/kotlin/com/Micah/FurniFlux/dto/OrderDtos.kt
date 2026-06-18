package com.Micah.FurniFlux.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class OrderItemRequest(
    val productId: Long,

    @field:Positive(message="Quantity must be more than zero.")
    val quantity: Int,
)

data class OrderRequest(
    @field:NotEmpty(message="Order must not be empty.")
    val items: List<OrderItemRequest>,
)

data class OrderItemResponse(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val priceAtPurchase: BigDecimal
)

data class OrderResponse(
    val orderId: Long,
    val status: String,
    val totalAmount: BigDecimal,
    val items: List<OrderItemResponse>
)