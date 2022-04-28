package com.github.kotpay.gateway.web

import com.github.kotpay.gateway.exception.ErrorCode
import com.github.kotpay.gateway.exception.LocalizableException
import com.github.kotpay.gateway.web.common.ErrorHolder
import com.github.kotpay.gateway.web.common.ErrorResponse
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private final val logger = KotlinLogging.logger {}

    @ExceptionHandler(value = [LocalizableException::class])
    fun handleLocalizableException(exception: LocalizableException): ResponseEntity<Any> {
        return handleExceptionInternal(exception, exception.errorCode.code)
    }

    private fun handleExceptionInternal(exception: Throwable, code: String): ResponseEntity<Any> {
        logger.error(exception) { "Caught unhandled exception" }
        val errorHolder = ErrorHolder(code, "Internal error")
        logger.info { "Returning error holder $errorHolder" }
        val errorResponse = ErrorResponse<Any>(errorHolder)
        return ResponseEntity.ok(errorResponse)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(exception: Exception): ResponseEntity<Any> {
        logger.error(exception) { "Caught unhandled exception" }
        val errorHolder = ErrorHolder(ErrorCode.SERVER_INTERNAL.code, "Internal error")
        logger.info { "Returning error holder $errorHolder" }
        val errorResponse = ErrorResponse<Any>(errorHolder)
        return ResponseEntity.ok(errorResponse)
    }
}