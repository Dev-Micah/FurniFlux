package com.Micah.FurniFlux.service

import com.Micah.FurniFlux.repository.OrderRepository
import com.Micah.FurniFlux.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class OrderService (
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
){

}