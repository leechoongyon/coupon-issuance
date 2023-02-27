package io.simple.coupon.core.api.domain

class CouponIssuanceRequestCommand {

    class Request(
        val data: String
    ) {

    }

    data class Response(
        val data: String
    ) {

    }
}