package com.github.kotpay.gateway.web.controller.payment.dto

import com.github.kotpay.gateway.entity.Order
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Response containing payment results")
data class PaymentCardResponse(
    @Schema(description = "Order status", required = true, example = "CREATED")
    val status: Order.Status
)
