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

    INTERNAL_SERVER_ERROR(500, "서버 에러입니다"),

    // 1000 [Product]

    // 1100 [Product Image]
    PRODUCT_IMAGE_SIZE_EXCEED_MAX(1100, "상품 이미지의 개수가 10개 미만이여야 합니다"),
    PRODUCT_IMAGE_THUMBNAIL_NOT_ONE(1101, "상품 기본 이미지 개수가 1개여야 합니다"),
    PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL(1102, "기본 이미지는 삭제할 수 없습니다"),

    // 1200 [Product Color]
    PRODUCT_COLOR_DUPLICATE_CODE_IN_SAME_PRODUCT(1200, "한 상품에 대한 적용 가능 색상이 중복되었습니다"),

    // 1300 [Product Material]
    PRODUCT_MATERIAL_DUPLICATE_CODE_IN_SAME_PRODUCT(1300, "한 상품에 대한 적용 가능 재질이 중복되었습니다"),

    // 1200 [Product Option]
    PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION(1200, "옵션 카테고리 안에는 최소 한 개 이상의 옵션이 필요합니다")
}