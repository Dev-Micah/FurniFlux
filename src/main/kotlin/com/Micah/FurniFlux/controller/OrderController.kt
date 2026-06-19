package com.Micah.FurniFlux.controller

import com.Micah.FurniFlux.dto.OrderRequest
import com.Micah.FurniFlux.dto.OrderResponse
import com.Micah.FurniFlux.model.User
import com.Micah.FurniFlux.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    fun placeOrder(
        @Valid  @RequestBody request: OrderRequest,
        @AuthenticationPrincipal currentUser: User
    ): ResponseEntity<OrderResponse> {
        val response = orderService.createOrder(request, currentUser)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping
    fun getMyOrders(
        @AuthenticationPrincipal currentUser: User
    ): ResponseEntity<List<OrderResponse>>{
        return ResponseEntity.ok(orderService.getOrderHistory(currentUser))
    }

}