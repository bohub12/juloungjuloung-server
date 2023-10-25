package com.juloungjuloung.juju

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JujuApiApplication

fun main(args: Array<String>) {
    runApplication<JujuApiApplication>(*args)
}