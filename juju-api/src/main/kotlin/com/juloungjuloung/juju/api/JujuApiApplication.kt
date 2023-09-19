package com.juloungjuloung.juju.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["com.juloungjuloung.juju.domain"])
class JujuApiApplication

fun main(args: Array<String>) {
    runApplication<JujuApiApplication>(*args)
}