package com.juloungjuloung.juju.exception

import com.juloungjuloung.juju.response.ApiResponseCode

class BusinessLogicException(code: ApiResponseCode) : IllegalStateException()