package com.juloungjuloung.juju.exceptionhandler

import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.fail
import com.juloungjuloung.juju.response.ApiResponseCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.ConversionFailedException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ExceptionHandler {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    /**
     * custom exception
     */
    @ExceptionHandler(BusinessLogicException::class)
    @ResponseStatus(BAD_REQUEST)
    fun exceptionHandle(e: BusinessLogicException): ApiResponse<Boolean> {
        logger.warn("Business logic error", e)
        return fail(e.code)
    }

    @ExceptionHandler(value = [MethodArgumentTypeMismatchException::class, ConversionFailedException::class])
    @ResponseStatus(BAD_REQUEST)
    fun exceptionHandle(): ApiResponse<Boolean> {
        logger.error("Message parsing error")
        return fail(ApiResponseCode.PRODUCT_VALID_BAD_PRODUCT_TYPE_ENUM_IN_SAVE_CONDITION)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun exceptionHandle(e: Exception): ApiResponse<Boolean> {
        logger.error("Unexpected error occurred", e)
        return fail(ApiResponseCode.INTERNAL_SERVER_ERROR)
    }
}