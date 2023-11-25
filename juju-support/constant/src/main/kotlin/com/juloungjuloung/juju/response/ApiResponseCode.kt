package com.juloungjuloung.juju.response

enum class ApiResponseCode(
    val code: Int,
    val message: String
) {

    // success
    SUCCESS(200, "성공"),

    // Common
    BAD_REQUEST_ENUM(400, "잘못된 Enum 값입니다."),

    INTERNAL_SERVER_ERROR(500, "서버 에러입니다")
}