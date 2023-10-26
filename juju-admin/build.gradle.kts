tasks {
    bootJar {
        enabled = true
        archiveFileName.set("admin-application.jar")
    }

    jar {
        enabled = false
    }
}

dependencies {
    implementation(project(":juju-core:application"))

    implementation(project(":juju-support:utils"))
    implementation(project(":juju-support:constants"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}