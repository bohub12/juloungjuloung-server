tasks {
    bootJar {
        enabled = true
        archiveFileName.set("application.jar")
    }

    jar {
        enabled = false
    }
}

dependencies {
    implementation(project(":juju-domain"))

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    // Monitoring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}