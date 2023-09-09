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

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
}