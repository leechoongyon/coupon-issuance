package io.simple.coupon.core.api.controller.v2

class CouponIssuanceDto {
    data class CouponIssuanceRequest(
        val userId: String
    ) {
    }

    data class CouponIssuanceResponse(
        val couponId: String
    ) {
    }
}