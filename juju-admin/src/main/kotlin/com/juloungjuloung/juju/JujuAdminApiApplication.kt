package com.juloungjuloung.juju

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JujuAdminApiApplication

fun main(args: Array<String>) {
    runApplication<JujuAdminApiApplication>(*args)
}