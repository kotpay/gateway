package com.github.kotpay.gateway.web.common

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema


interface Response<T> {
    @JsonProperty(index = 0)
    @Schema(description = "Flag denoting whether operation was successful", required = true)
    fun getSuccess(): Boolean

    @Schema(description = "Response payload if success is true", required = false)
    fun getData(): T? = null

    @Schema(description = "Error holder if success is false", required = false)
    fun getError(): ErrorHolder? = null
}