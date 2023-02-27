package io.simple.coupon.core.api.controller.v1

class CouponIssuanceDto {
    data class CouponIssuanceRequest(
        val data: String
    ) {
    }

    data class CouponIssuanceResponse(
        val data: String
    ) {
    }
}