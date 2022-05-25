package com.github.kotpay.gateway.exception

class LocalizableException(val errorCode: ErrorCode, throwable: Throwable?) : Exception(errorCode.code, throwable)