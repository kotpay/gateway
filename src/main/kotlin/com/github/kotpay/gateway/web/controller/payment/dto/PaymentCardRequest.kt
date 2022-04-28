package com.github.kotpay.gateway.web.controller.payment.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request containing card info")
data class PaymentCardRequest(
    @Schema(description = "UUID order identifier", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
    val orderId: String,
    @Schema(description = "Card number", required = true, example = "5555555555555599")
    val pan: String,
    @Schema(description = "Card expiration in format YYYYMM", required = true, example = "202214")
    val expiration: String,
    @Schema(description = "Card CVC code", required = true, example = "123")
    val cvc: String,
    @Schema(description = "Card holder name", required = true, example = "JAMES BOND")
    val cardholder: String,
)
