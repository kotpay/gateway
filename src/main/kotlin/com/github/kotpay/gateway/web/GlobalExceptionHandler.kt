package com.github.kotpay.gateway.web

import com.github.kotpay.gateway.exception.ErrorCode
import com.github.kotpay.gateway.exception.LocalizableException
import com.github.kotpay.gateway.web.common.ErrorHolder
import com.github.kotpay.gateway.web.common.ErrorResponse
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private final val log = KotlinLogging.logger {}

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ErrorCode.SERVER_INTERNAL)
    }

    @ExceptionHandler(value = [LocalizableException::class])
    fun handleLocalizableException(exception: LocalizableException): ResponseEntity<Any> {
        return handleExceptionInternal(exception, exception.errorCode)
    }

    fun handleExceptionInternal(exception: Exception, errorCode: ErrorCode): ResponseEntity<Any> {
        log.error(exception) { "Caught exception" }
        val errorHolder = ErrorHolder(errorCode.code, "Internal error")
        log.info { "Returning error payload $errorHolder" }
        val errorResponse = ErrorResponse<Any>(errorHolder)
        return ResponseEntity.ok(errorResponse)
    }
}