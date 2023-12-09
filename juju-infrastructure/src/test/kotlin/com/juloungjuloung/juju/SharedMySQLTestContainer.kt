package com.juloungjuloung.juju

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

abstract class SharedMySQLTestContainer {

    companion object {
        const val DATABASE_NAME: String = "juju-test-database"
        const val USERNAME: String = "root"
        const val PASSWORD: String = "password"

        @Container
        @JvmStatic
        val MYSQL_CONTAINER = MySQLContainer<Nothing>("mysql:8")
            .apply { withDatabaseName(DATABASE_NAME) }
            .apply { withUsername(USERNAME) }
            .apply { withPassword(PASSWORD) }
            .apply { start() }

        @DynamicPropertySource
        fun mySQLProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl)
            registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername)
            registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword)
        }
    }
}