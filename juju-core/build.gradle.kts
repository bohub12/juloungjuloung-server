dependencies {
    runtimeOnly(project(":juju-infrastructure"))

    implementation(project(":juju-support:aws"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
}