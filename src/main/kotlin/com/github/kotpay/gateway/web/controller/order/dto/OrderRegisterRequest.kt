package com.github.kotpay.gateway.web.controller.order.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request containing order info to register")
data class OrderRegisterRequest(
    @Schema(description = "Order amount in minor units", required = true, example = "1000")
    val amount: Long,
    @Schema(description = "ISO 4217 currency code", required = true, example = "643")
    val currency: String,
    @Schema(
        description = "Merchant return url to redirect user after payment",
        required = true,
        example = "https://yandex.ru"
    )
    val returnUrl: String,
)
