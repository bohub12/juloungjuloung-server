package com.juloungjuloung.juju.exception

import com.juloungjuloung.juju.response.ApiResponseCode

class BadRequestException(code: ApiResponseCode) : IllegalArgumentException()