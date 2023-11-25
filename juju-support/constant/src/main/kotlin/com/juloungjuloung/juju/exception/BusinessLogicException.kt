package com.juloungjuloung.juju.exception

import com.juloungjuloung.juju.response.ApiResponseCode

class BusinessLogicException(val code: ApiResponseCode) : IllegalStateException()