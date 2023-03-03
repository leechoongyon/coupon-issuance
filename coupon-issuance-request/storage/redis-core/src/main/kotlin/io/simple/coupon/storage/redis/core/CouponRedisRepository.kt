package io.simple.coupon.storage.redis.core

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Repository

@Repository
class CouponRedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val addScript: RedisScript<String> = DefaultRedisScript(
        """
        local count = redis.call('ZCARD', KEYS[1])
        if count >= tonumber(ARGV[1]) then
            return 'FULL'
        end
        return redis.call('ZADD', KEYS[1], ARGV[2], ARGV[3])
        """,
        String::class.java
    )

    init {
        redisTemplate.valueSerializer = StringRedisSerializer()
    }

    fun requestIssueCoupon(userId: String): Boolean {
        val result: String? = redisTemplate.execute(
            addScript,
            listOf("issued_coupons"),
            1000.toString(),
            userId,
            System.currentTimeMillis().toDouble().toString()
        )
        return result != null && result != "FULL"
    }
}