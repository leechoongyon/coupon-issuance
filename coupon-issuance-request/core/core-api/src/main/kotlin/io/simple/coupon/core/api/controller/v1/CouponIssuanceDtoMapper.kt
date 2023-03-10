package io.simple.coupon.core.api.controller.v1

import io.simple.coupon.core.api.controller.v1.CouponIssuanceDto.CouponIssuanceRequest
import io.simple.coupon.core.api.domain.CouponIssuanceRequestCommand
import org.mapstruct.Mapper

@Mapper
interface CouponIssuanceDtoMapper {
    fun convertToCouponIssuanceRequest(request: CouponIssuanceRequest): CouponIssuanceRequestCommand.Request
    fun convertCouponIssuanceResponse(response: CouponIssuanceRequestCommand.Response):
            CouponIssuanceDto.CouponIssuanceResponse
}
