package io.simple.coupon.core.api.controller.example

import io.simple.coupon.storage.redis.core.SortedSetExampleRedisRepository
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RedisExampleController(
    val sortedSetExampleRedisRepository: SortedSetExampleRedisRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/example/sortedSet")
    fun addRedisSortedSetExample(
        @RequestBody request: RedisExampleDto.AddRequest
    ) {
        log.info("addRedisSortedSetExample request : {}", request)
        sortedSetExampleRedisRepository.addValueToSortedSet(request.key, request.value)
    }
}