package com.github.kotpay.gateway.exception

enum class ErrorCode(val code: String) {
    SERVER_INTERNAL("server.internal"),
    CLIENT_INVALID_AMOUNT("client.amount.invalid"),
    CLIENT_CURRENCY_INVALID("client.currency.invalid"),
    CLIENT_ORDER_MISSING("client.order.missing"),
    CLIENT_EXPIRATION_INVALID("client.expiration.invalid"),
}