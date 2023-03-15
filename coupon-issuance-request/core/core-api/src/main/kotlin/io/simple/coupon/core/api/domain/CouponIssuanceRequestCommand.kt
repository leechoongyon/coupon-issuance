package io.simple.coupon.core.api.domain

class CouponIssuanceRequestCommand {

    class Request(
        val userId: String
    ) {

    }

    data class Response(
        val data: String
    ) {

    }
}