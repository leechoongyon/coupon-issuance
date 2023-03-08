package io.simple.coupon.storage.redis.core

import org.apache.logging.log4j.util.Strings
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CouponRedisRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    private val AVAILABLE_COUPON_REDIS_KEY: String = "available-coupon"
    private val COUPON_USER_MAPPING_REDIS_KEY: String = "coupon-user-mapping"

    private val addScript: RedisScript<String> = DefaultRedisScript(
        """
       local function isempty(s)
            return s == nil or s == ''
        end

        local count = redis.call('ZCARD', KEYS[1])
        if tonumber(count) >= 1000 then
            return 'FULL'
        end
        local result = redis.call('ZADD', KEYS[1], ARGV[2], ARGV[3])
        return 'ADD'
        """,
        String::class.java
    )

    private val requestPreGeneratedCouponIssuanceScript: RedisScript<String> = DefaultRedisScript(
        """
            -- 체크할 user Id를 매개변수로 받음
            local userId = ARGV[1]

            -- Hash에서 userId가 있는지 확인하고, 있다면 "exist"를 리턴
            local check = redis.call('HEXISTS', KEYS[2], ARGV[1])
            if check == 1 then
                return "exist"
            end

            -- Set에서 쿠폰 ID를 가져와서 제거
            local couponId = redis.call('SPOP', KEYS[1])

            -- Hash에 쿠폰 ID를 저장
            redis.call('HSET', KEYS[2], ARGV[1], couponId)

            -- 발급된 쿠폰 ID 리턴
            return couponId
        """,
        String::class.java
    )

    fun requestIssueCoupon(userId: String): String? {
        val result: String? = redisTemplate.execute(
            addScript,
            listOf("coupon_issueance"), // KEYS[1]
            "1000", // ARGV[1]
            System.currentTimeMillis().toString(), // ARGV[2]
            userId  // ARGV[3]
        )
        return Optional.ofNullable(result).orElse(Strings.EMPTY)
    }

    fun requestPreGeneratedCouponIssuance(userId: String): String {
        val result: String? = redisTemplate.execute(
            requestPreGeneratedCouponIssuanceScript,
            listOf(AVAILABLE_COUPON_REDIS_KEY, COUPON_USER_MAPPING_REDIS_KEY),  // KEYS[1~2]
            userId
        )
        return Optional.ofNullable(result).orElse(Strings.EMPTY)
    }
}
