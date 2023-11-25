package com.juloungjuloung.juju.exceptionhandler

import com.juloungjuloung.juju.exception.BadRequestException
import com.juloungjuloung.juju.exception.BusinessLogicException
import com.juloungjuloung.juju.response.ApiResponse
import com.juloungjuloung.juju.response.ApiResponse.Companion.fail
import com.juloungjuloung.juju.response.ApiResponseCode
import org.springframework.core.convert.ConversionFailedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun exceptionHandle(e: Exception): ApiResponse<Boolean> {
        // TODO : logging stack trace
        e.printStackTrace()
        return fail(ApiResponseCode.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [MethodArgumentTypeMismatchException::class, ConversionFailedException::class])
    fun exceptionHandle(): ApiResponse<Boolean> {
        // TODO : logging stack trace
        return fail(ApiResponseCode.BAD_REQUEST_ENUM)
    }

    @ExceptionHandler(BadRequestException::class)
    fun exceptionHandle(e: BadRequestException): ApiResponse<Boolean> {
        // TODO : logging stack trace
        e.printStackTrace()
        return fail(e.code)
    }

    @ExceptionHandler(BusinessLogicException::class)
    fun exceptionHandle(e: BusinessLogicException): ApiResponse<Boolean> {
        // TODO : logging stack trace
        e.printStackTrace()
        return fail(e.code)
    }
}