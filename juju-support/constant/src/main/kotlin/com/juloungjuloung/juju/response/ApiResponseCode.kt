package com.juloungjuloung.juju.response

enum class ApiResponseCode(
    val code: Int,
    val message: String
) {

    // success
    SUCCESS(200, "성공"),

    // Common
    BAD_REQUEST(400, "잘못된 Enum 값입니다."),
    NOT_VALID_REQUEST_DATA(400, "잘못된 요청 값입니다")

    // Member 관련 Error Response

    //
}