package io.simple.coupon.core.api.controller.v1

import io.simple.coupon.core.api.controller.v1.request.ExampleRequestDto
import io.simple.coupon.core.api.controller.v1.response.ExampleResponseDto
import io.simple.coupon.core.api.domain.ExampleData
import io.simple.coupon.core.api.domain.ExampleService
import io.simple.coupon.core.api.support.response.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
class ExampleController(
    val exampleExampleService: ExampleService
) {
    @GetMapping("/get/{exampleValue}")
    fun exampleGet(
        @PathVariable exampleValue: String,
        @RequestParam exampleParam: String
    ): ApiResponse<ExampleResponseDto> {
        val result = exampleExampleService.processExample(ExampleData(exampleValue, exampleParam))
        return ApiResponse.success(ExampleResponseDto(result.data))
    }

    @PostMapping("/post")
    fun examplePost(
        @RequestBody request: ExampleRequestDto
    ): ApiResponse<ExampleResponseDto> {
        val result = exampleExampleService.processExample(request.toExampleData())
        return ApiResponse.success(ExampleResponseDto(result.data))
    }
}
