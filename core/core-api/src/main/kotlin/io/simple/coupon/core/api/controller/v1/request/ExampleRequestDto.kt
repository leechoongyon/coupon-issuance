package io.simple.coupon.core.api.controller.v1.request

import io.simple.coupon.core.api.domain.ExampleData

data class ExampleRequestDto(
    val data: String
) {
    fun toExampleData(): ExampleData {
        return ExampleData(data, data)
    }
}
