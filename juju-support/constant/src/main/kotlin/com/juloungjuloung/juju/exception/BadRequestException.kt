package com.juloungjuloung.juju.exception

import com.juloungjuloung.juju.response.ApiResponseCode

class BadRequestException(val code: ApiResponseCode) : RuntimeException()