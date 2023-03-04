package io.simple.coupon.core.api.domain

import io.simple.coupon.core.api.domain.CouponIssuanceRequestCommand.Response
import io.simple.coupon.storage.redis.core.CouponRedisRepository
import org.springframework.stereotype.Service

@Service
class CouponIssuanceService(
    val couponRedisRepository: CouponRedisRepository
) {
    fun requestCouponIssuance(
        request: CouponIssuanceRequestCommand.Request
    ): Response {
        var result = couponRedisRepository.requestIssueCoupon(request.data)
        return CouponIssuanceRequestCommand.Response(request.data)
    }
}