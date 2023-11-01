package com.juloungjuloung.juju

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class JujuAdminApiApplication

fun main(args: Array<String>) {
    runApplication<JujuAdminApiApplication>(*args)
}