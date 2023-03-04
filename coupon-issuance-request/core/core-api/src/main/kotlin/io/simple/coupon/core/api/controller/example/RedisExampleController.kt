package io.simple.coupon.core.api.controller.example

import io.simple.coupon.storage.redis.core.SortedSetExampleRedisRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisExampleController(
    val sortedSetExampleRedisRepository: SortedSetExampleRedisRepository
) {
    @PostMapping("/example/sortedSet")
    fun addRedisSortedSetExample(
        @RequestBody request: RedisExampleDto.AddRequest
    ) {
        sortedSetExampleRedisRepository.addValueToSortedSet(request.key, request.value)
    }
}