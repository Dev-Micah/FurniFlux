package com.Micah.FurniFlux.service

import com.Micah.FurniFlux.dto.OrderItemResponse
import com.Micah.FurniFlux.dto.OrderRequest
import com.Micah.FurniFlux.dto.OrderResponse
import com.Micah.FurniFlux.exception.ResourceNotFoundException
import com.Micah.FurniFlux.model.Order
import com.Micah.FurniFlux.model.Product
import com.Micah.FurniFlux.model.User
import com.Micah.FurniFlux.repository.OrderRepository
import com.Micah.FurniFlux.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
){
    @Transactional
    fun createOrder(request: OrderRequest, currentUser: User ): OrderResponse{

        val order = Order(user = currentUser)

        // 2. Process every individual line item
        for (itemRequest in request.items) {
            val product = productRepository.findById(itemRequest.productId)
                .orElseThrow { ResourceNotFoundException("Product with ID ${itemRequest.productId} not found.") }


            if (product.stockQuantity < itemRequest.quantity) {
                throw IllegalArgumentException(
                    "Insufficient stock for item: ${product.name}. " + "Remaining: ${product.stockQuantity}"
                )
            }

            product.stockQuantity -= itemRequest.quantity
            productRepository.save(product)

            order.addItem(product, itemRequest.quantity, product.price)

        }

        val savedOrder = orderRepository.save(order)
        return mapToResponse(savedOrder)

    }

    fun getOrderHistory(currentUser: User): List<OrderResponse>{
        return orderRepository.findByUserOrderByCreatedAtDesc(currentUser).map { mapToResponse(it) }
    }
    private fun mapToResponse(order: Order): OrderResponse {
        return OrderResponse(
            orderId = order.id!!,
            status = order.status.name,
            totalAmount = order.totalAmount,
            items = order.items.map { item ->
                OrderItemResponse(
                    productId = item.product.id!!,
                    productName = item.product.name,
                    quantity = item.quantity,
                    priceAtPurchase = item.price
                )
            }
        )
    }
}