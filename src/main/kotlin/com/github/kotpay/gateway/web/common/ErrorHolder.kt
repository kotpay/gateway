package com.github.kotpay.gateway.web.common

import io.swagger.v3.oas.annotations.media.Schema

data class ErrorHolder(
    @Schema(description = "Machine readable error code", example = "server.internal", required = true)
    val code: String,
    @Schema(description = "Human readable error description", example = "Server not responding", required = true)
    val description: String
)