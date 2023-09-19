tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}

allOpen {
    annotation("com.juloungjuloung.juju.domain.infrastructure.config.AllOpen")
}

dependencies {

    // Spring Data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Database
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.postgresql:postgresql")

    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
}