package io.simple.coupon.core.api.domain

import io.simple.coupon.core.api.domain.CouponIssuanceRequestCommand.Response
import org.springframework.stereotype.Service

@Service
class CouponIssuanceService {
    fun requestCouponIssuance(
        request: CouponIssuanceRequestCommand.Request
    ): Response {
        return CouponIssuanceRequestCommand.Response(request.data)
    }
}