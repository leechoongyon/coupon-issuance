package io.simple.coupon.core.api.support.error

class CoreApiException(
    val errorType: ErrorType,
    val data: Any? = null
) : RuntimeException(errorType.message)
