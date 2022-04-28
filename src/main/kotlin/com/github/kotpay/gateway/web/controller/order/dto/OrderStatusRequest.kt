package com.github.kotpay.gateway.web.controller.order.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request containing order identifier")
data class OrderStatusRequest(
    @Schema(description = "UUID order identifier", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
    val orderId: String,
)
