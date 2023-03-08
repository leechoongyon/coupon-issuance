package io.simple.coupon.core.api.controller.v2

import io.simple.coupon.core.api.controller.v2.CouponIssuanceDto.CouponIssuanceRequest
import io.simple.coupon.core.api.controller.v2.CouponIssuanceDto.CouponIssuanceResponse
import io.simple.coupon.core.api.domain.CouponIssuanceService
import io.simple.coupon.core.api.support.response.ApiResponse
import org.mapstruct.factory.Mappers
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponIssuanceController(
    val couponIssuanceService: CouponIssuanceService
) {
    @PostMapping("api/v2/coupon/issuance/request")
    fun requestCouponIssuance(
        @RequestBody request: CouponIssuanceRequest
    ): ApiResponse<CouponIssuanceResponse> {
        val couponIssuanceDtoMapper = Mappers.getMapper(CouponIssuanceDtoMapper::class.java)
        var couponIssuanceRequest = couponIssuanceDtoMapper.convertToCouponIssuanceRequest(request);
        var result = couponIssuanceService.requestCouponIssuance(couponIssuanceRequest);
        return ApiResponse.success(couponIssuanceDtoMapper.convertCouponIssuanceResponse(result))
    }
}