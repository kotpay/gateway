package com.github.kotpay.gateway.exception

class LocalizableException(val errorCode: ErrorCode, throwable: Throwable?) : Throwable(errorCode.code, throwable)