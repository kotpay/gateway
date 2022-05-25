package com.github.kotpay.gateway.web.common

data class ErrorResponse<T>(private val errorHolder: ErrorHolder) : Response<T> {
    override fun getSuccess(): Boolean = false
    override fun getError(): ErrorHolder = errorHolder
}