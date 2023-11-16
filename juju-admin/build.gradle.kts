tasks {
    bootJar {
        enabled = true
        archiveFileName.set("admin-application.jar")
    }

    jar {
        enabled = false
    }
}

val swaggerVersion: String by project
val jasyptVersion: String by project

dependencies {
    implementation(project(":juju-core:application"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$swaggerVersion")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:$jasyptVersion")
}