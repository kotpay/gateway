package com.github.kotpay.gateway.dao

import com.github.kotpay.gateway.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findOrderByOrderId(orderId: String): Order?
}