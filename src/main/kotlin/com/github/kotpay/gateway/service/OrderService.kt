package com.github.kotpay.gateway.service

import com.github.kotpay.gateway.dao.OrderRepository
import com.github.kotpay.gateway.entity.Order
import com.github.kotpay.gateway.exception.ErrorCode
import com.github.kotpay.gateway.exception.LocalizableException
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import javax.transaction.Transactional

@Component
class OrderService(
    private val orderRepository: OrderRepository,
    @Value("\${form.url}") private val formUrl: String,
) {
    private final val logger = KotlinLogging.logger {}
    private final val expirationFormat = DateTimeFormatter.ofPattern("yyyyMM")

    fun registerOrder(amount: Long, currencyCode: String, returnUrl: String): String {
        if (amount <= 0) {
            throw LocalizableException(ErrorCode.CLIENT_INVALID_AMOUNT, null)
        }
        try {
            val currency = Currency.getInstance(currencyCode)
            logger.info { "Parsed currency $currency" }
        } catch (e: IllegalArgumentException) {
            throw LocalizableException(ErrorCode.CLIENT_CURRENCY_INVALID, e)
        }
        val order = Order()
        order.orderId = UUID.randomUUID().toString()
        order.amount = amount
        order.currencyCode = currencyCode
        order.returnUrl = returnUrl
        order.status = Order.Status.CREATED
        val savedOrder = orderRepository.save(order)
        logger.info { "Saved order ${order.id}" }
        return savedOrder.orderId!!
    }

    fun getOrder(orderId: String): Order {
        return orderRepository.findOrderByOrderId(orderId)
            ?: throw LocalizableException(ErrorCode.CLIENT_ORDER_MISSING, null)
    }

    @Transactional
    fun payByCard(orderId: String, expiration: String) {
        val order: Order = orderRepository.findOrderByOrderId(orderId)
            ?: throw LocalizableException(ErrorCode.CLIENT_ORDER_MISSING, null)
        try {
            val localDate = YearMonth.parse(expiration, expirationFormat)
            logger.info { "Parsed date $localDate" }
            if (localDate.isBefore(YearMonth.now())) {
                throw LocalizableException(ErrorCode.CLIENT_EXPIRATION_INVALID, null)
            }
        } catch (e: DateTimeParseException) {
            throw LocalizableException(ErrorCode.CLIENT_EXPIRATION_INVALID, e)
        }
        order.status = Order.Status.DEPOSITED
    }

    fun getFormUrl(orderId: String): String {
        return "$formUrl?orderId=$orderId"
    }
}