dependencies {
    implementation(project(":juju-core:domain"))
    runtimeOnly(project(":juju-infrastructure"))

    implementation(project(":juju-support:aws"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
}