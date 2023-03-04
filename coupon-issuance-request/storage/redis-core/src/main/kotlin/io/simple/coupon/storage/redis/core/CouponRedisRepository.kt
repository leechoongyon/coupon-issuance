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

    fun requestIssueCoupon(userId: String): String? {
        val result: String? = redisTemplate.execute(
            addScript,
            listOf("issued_coupons"),
            "1000",
            System.currentTimeMillis().toString(),
            userId
        )
        return Optional.ofNullable(result).orElse(Strings.EMPTY)
    }
}
