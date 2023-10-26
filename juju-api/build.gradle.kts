tasks {
    bootJar {
        enabled = true
        archiveFileName.set("application.jar")
    }

    jar {
        enabled = false
    }
}

val swaggerVersion: String by project

dependencies {
    implementation(project(":juju-core:domain"))

    implementation(project(":juju-support:utils"))
    implementation(project(":juju-support:constants"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$swaggerVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}