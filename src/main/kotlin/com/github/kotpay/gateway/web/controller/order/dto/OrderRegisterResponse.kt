package com.github.kotpay.gateway.web.controller.order.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Response containing register results")
data class OrderRegisterResponse(
    @Schema(description = "UUID order identifier", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
    val orderId: String,
    @Schema(description = "Payment page url to redirect user for payment", required = true, example = "https://ya.ru")
    val formUrl: String,
)
