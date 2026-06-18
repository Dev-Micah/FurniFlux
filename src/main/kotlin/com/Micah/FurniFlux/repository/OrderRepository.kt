package com.Micah.FurniFlux.repository

import com.Micah.FurniFlux.model.Order
import com.Micah.FurniFlux.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long>{
    fun findByUserOrderByCreatedAtDesc(user: User): List<Order>
}