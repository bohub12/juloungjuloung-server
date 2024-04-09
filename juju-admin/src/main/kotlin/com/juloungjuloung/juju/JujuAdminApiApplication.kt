package com.juloungjuloung.juju

import com.juloungjuloung.juju.config.CustomEncryptedPropertyDetector
import com.ulisesbocchio.jasyptspringboot.environment.StandardEncryptableEnvironment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class JujuAdminApiApplication

fun main(args: Array<String>) {
    // This environment is for springProperty in logback-spring.xml
    SpringApplicationBuilder()
        .environment(StandardEncryptableEnvironment.builder().detector(CustomEncryptedPropertyDetector()).build())
        .sources(JujuAdminApiApplication::class.java)
        .run(*args)
}