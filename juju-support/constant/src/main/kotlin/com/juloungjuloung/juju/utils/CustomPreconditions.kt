package com.juloungjuloung.juju.utils

import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponseCode

fun require(
    value: Boolean,
    errorResponseCode: ApiResponseCode
) {
    if (!value) {
        throw BusinessLogicException(errorResponseCode)
    }
}

fun <T : Any> requireNotNull(
    value: T?,
    errorResponseCode: ApiResponseCode
) {
    if (value == null) {
        throw BusinessLogicException(errorResponseCode)
    }
}