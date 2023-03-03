package io.simple.coupon.storage.redis.core

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class SortedSetExampleRedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun addValueToSortedSet(key: String, value: String) {
        val score: Double = Instant.now().toEpochMilli().toDouble()
        val ops: ZSetOperations<String, String> = redisTemplate.opsForZSet()
        ops.add(key, value, score)
    }
}