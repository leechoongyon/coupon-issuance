package io.simple.coupon.core.api.interfaces.v1

import io.simple.coupon.core.api.interfaces.v1.request.RequestCouponIssuanceRequestDto
import io.simple.coupon.core.api.interfaces.v1.response.RequestCouponIssuanceResponseDto
import io.simple.coupon.core.api.support.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponIssuanceRequestApiController {
    @PostMapping("/coupon-issuance/request")
    fun requestCouponIssuance(
        @RequestBody request: RequestCouponIssuanceRequestDto
    ): ApiResponse<RequestCouponIssuanceResponseDto> {
        return ApiResponse.success(RequestCouponIssuanceResponseDto("Coupon-issuance-request"))
    }
}