package io.simple.coupon.core.api.controller.example

class RedisExampleDto {
    data class AddRequest(
        val key: String,
        val value: String
    ) {

    }
}