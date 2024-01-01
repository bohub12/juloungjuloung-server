package com.juloungjuloung.juju.response

enum class ApiResponseCode(
    val code: Int,
    val message: String
) {

    // success
    SUCCESS(200, "성공"),

    // Common
    ILLEGAL_ARGUMENT(400, "잘못된 입력입니다"),
    BAD_REQUEST_ID(400, "잘못된 ID 입니다"),
    BAD_REQUEST_ENUM(400, "잘못된 Enum 값 입니다"),

    INTERNAL_SERVER_ERROR(500, "서버 에러입니다")
}