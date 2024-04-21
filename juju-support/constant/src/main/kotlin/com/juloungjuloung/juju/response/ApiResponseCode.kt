package com.juloungjuloung.juju.response

enum class ApiResponseCode(
    val code: Int,
    val message: String
) {
    // success
    SUCCESS(200, "성공"),

    // Common
    BAD_REQUEST_ID(400, "데이터를 찾지 못했습니다"),
    INTERNAL_SERVER_ERROR(500, "서버 문제로 인해 동작하지 않았습니다"),

    // 1000 [Product]
    PRODUCT_VALID_PRICE_MIN_ZERO(1000, "상품 가격은 0원 이상이어야 합니다"),
    PRODUCT_VALID_WEIGHT_MIN_ZERO(1001, "상품 무게는 0mg 이상이어야 합니다"),
    PRODUCT_VALID_THUMBNAIL_NOT_NULL_IF_DISPLAYED(1002, "상품 전시여부가 활성화되어 있을 땐, 대표이미지가 있어야 합니다"),
    PRODUCT_VALID_BAD_PRODUCT_TYPE_ENUM_IN_SAVE_CONDITION(1003, "상품 저장 시에 상품 타입 선택은 필수입니다"),

    // 1100 [Product Image]
    PRODUCT_IMAGE_SIZE_EXCEED_MAX(1100, "상품 이미지의 개수가 10개 미만이여야 합니다"),
    PRODUCT_IMAGE_THUMBNAIL_NOT_ONE(1101, "상품 대표 이미지 개수가 1개여야 합니다"),
    PRODUCT_IMAGE_REMOVE_CONDITION_THUMBNAIL(
        1102,
        "상품이 전시 중일 때에는 대표 이미지는 삭제할 수 없습니다. 상품 전시여부를 수정하시거나 대표 이미지를 변경하신 후 삭제해주시기 바랍니다"
    ),

    // 1200 [Product Option]
    PRODUCT_OPTION_REQUIRES_AT_LEAST_ONE_OPTION(1200, "옵션 카테고리에 최소 한 개 이상의 옵션이 필요합니다")
}