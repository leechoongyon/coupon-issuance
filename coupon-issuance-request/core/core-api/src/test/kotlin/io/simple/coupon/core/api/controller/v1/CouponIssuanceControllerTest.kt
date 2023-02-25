package io.simple.coupon.core.api.controller.v1


import io.mockk.mockk
import io.restassured.http.ContentType
import io.simple.coupon.core.api.controller.v1.request.CouponIssuanceRequestDto
import io.simple.coupon.core.api.domain.CouponIssuanceService
import io.simple.coupon.test.RestDocsTest
import io.simple.coupon.test.RestDocsUtils.requestPreprocessor
import io.simple.coupon.test.RestDocsUtils.responsePreprocessor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation


class CouponIssuanceControllerTest : RestDocsTest() {
    private lateinit var controller: CouponIssuanceController
    private lateinit var service: CouponIssuanceService

    @BeforeEach
    fun setUp() {
        service = mockk()
        controller = CouponIssuanceController(service)
        mockMvc = mockController(controller)
    }

    @Test
    fun testRequestCouponIssuance() {
        given()
            .contentType(ContentType.JSON)
            .body(CouponIssuanceRequestDto("Hello World"))
            .post("/coupon/issuance/request")
            .then()
            .status(HttpStatus.OK)
            .apply(
                document(
                    "CouponIssuanceRequestPost",
                    requestPreprocessor(),
                    responsePreprocessor(),

                    PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.STRING)
                            .description("Data Field")
                    ),

                    PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("result").type(JsonFieldType.STRING)
                            .description("ResultType"),
                        PayloadDocumentation.fieldWithPath("data.data").type(JsonFieldType.STRING)
                            .description("Result Data"),
                        PayloadDocumentation.fieldWithPath("error").type(JsonFieldType.STRING)
                            .ignored()
                    )
                )
            )
    }
}