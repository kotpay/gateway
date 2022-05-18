package com.github.kotpay.gateway.web.controller.order

import com.github.kotpay.gateway.service.OrderService
import com.github.kotpay.gateway.web.common.Response
import com.github.kotpay.gateway.web.common.SuccessResponse
import com.github.kotpay.gateway.web.controller.order.dto.OrderRegisterRequest
import com.github.kotpay.gateway.web.controller.order.dto.OrderRegisterResponse
import com.github.kotpay.gateway.web.controller.order.dto.OrderStatusRequest
import com.github.kotpay.gateway.web.controller.order.dto.OrderStatusResponse
import io.swagger.v3.oas.annotations.Operation
import mu.KotlinLogging
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(originPatterns = ["*"])
@RequestMapping("/order", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class OrderController(
    private val orderService: OrderService,
) {
    private final val logger = KotlinLogging.logger {}

    @PostMapping("/register.do")
    @Operation(summary = "Registers order and returns url to payment page")
    fun register(@RequestBody request: OrderRegisterRequest): Response<OrderRegisterResponse> {
        logger.info { "Received order register request with payload $request" }
        val orderId = orderService.registerOrder(request.amount, request.currency, request.returnUrl)
        val formUrl = orderService.getFormUrl(orderId)
        val orderRegisterResponse = OrderRegisterResponse(orderId, formUrl)
        logger.info { "Returning order register response with payload $orderRegisterResponse" }
        return SuccessResponse(orderRegisterResponse)
    }

    @PostMapping("/status.do")
    @Operation(summary = "Returns order status")
    fun status(@RequestBody orderStatusRequest: OrderStatusRequest): Response<OrderStatusResponse> {
        logger.info { "Received order status request with payload $orderStatusRequest" }
        val order = orderService.getOrder(orderStatusRequest.orderId)
        val response = OrderStatusResponse(order.status!!, order.amount!!, order.currencyCode!!, order.returnUrl!!)
        logger.info { "Returning order status response with payload $response" }
        return SuccessResponse(response)
    }
}