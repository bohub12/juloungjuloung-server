package com.juloungjuloung.juju.common.response

enum class ApiResponseCode(
    val code: Int,
    val message: String
) {

    // success
    SUCCESS(200, "성공"),

    // Common
    NOT_VALID_REQUEST_DATA(400, "잘못된 요청 값입니다")

    // Member 관련 Error Response

    //
}