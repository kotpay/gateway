package com.github.kotpay.gateway.web.controller.payment

import com.github.kotpay.gateway.entity.Order
import com.github.kotpay.gateway.service.OrderService
import com.github.kotpay.gateway.web.common.Response
import com.github.kotpay.gateway.web.common.SuccessResponse
import com.github.kotpay.gateway.web.controller.payment.dto.PaymentCardRequest
import com.github.kotpay.gateway.web.controller.payment.dto.PaymentCardResponse
import io.swagger.v3.oas.annotations.Operation
import mu.KotlinLogging
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment", consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
class PaymentController(
    private val orderService: OrderService,
) {
    private final val logger = KotlinLogging.logger {}

    @PostMapping("/card.do")
    @Operation(summary = "Payment by credit card")
    fun card(paymentCardRequest: PaymentCardRequest): Response<PaymentCardResponse> {
        logger.info { "Received payment card request with payload $paymentCardRequest" }
        orderService.payByCard(paymentCardRequest.orderId, paymentCardRequest.expiration)
        val paymentCardResponse = PaymentCardResponse(Order.Status.DEPOSITED)
        logger.info { "Returning payment card response with payload $paymentCardResponse" }
        return SuccessResponse(paymentCardResponse)
    }
}