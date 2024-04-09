package com.juloungjuloung.juju.response

import com.fasterxml.jackson.annotation.JsonInclude

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val pageResponse: PageResponse?
) {
    companion object {
        fun <T> success(data: T) =
            ApiResponse(
                code = ApiResponseCode.SUCCESS.code,
                message = ApiResponseCode.SUCCESS.message,
                data = data,
                pageResponse = null
            )

        fun <T> success(
            data: T,
            pageResponse: PageResponse?
        ): ApiResponse<T> {
            return ApiResponse(
                code = ApiResponseCode.SUCCESS.code,
                message = ApiResponseCode.SUCCESS.message,
                data = data,
                pageResponse = pageResponse
            )
        }

        fun <T> fail(responseCode: ApiResponseCode): ApiResponse<T> {
            return ApiResponse(
                code = responseCode.code,
                message = responseCode.message,
                data = null,
                pageResponse = null
            )
        }
    }
}

data class PageResponse(
    val page: Int,
    val size: Int,
    val totalPageCount: Int
)