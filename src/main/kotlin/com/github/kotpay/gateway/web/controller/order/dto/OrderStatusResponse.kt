package com.github.kotpay.gateway.web.controller.order.dto

import com.github.kotpay.gateway.entity.Order
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Response containing order info")
data class OrderStatusResponse(
    @Schema(description = "Order status", required = true, example = "CREATED")
    val status: Order.Status,
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
