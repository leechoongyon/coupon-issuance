package io.simple.coupon.core.api.controller.v1

import io.simple.coupon.core.api.controller.v1.request.CouponIssuanceRequestDto
import io.simple.coupon.core.api.controller.v1.response.CouponIssuanceResponseDto
import io.simple.coupon.core.api.domain.CouponIssuanceService
import io.simple.coupon.core.api.support.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponIssuanceController(
    val couponIssuanceService: CouponIssuanceService
) {
    @PostMapping("/coupon/issuance/request")
    fun requestCouponIssuance(
        @RequestBody request: CouponIssuanceRequestDto
    ): ApiResponse<CouponIssuanceResponseDto> {
        return ApiResponse.success(CouponIssuanceResponseDto("Coupon-issuance-success"))
    }
}