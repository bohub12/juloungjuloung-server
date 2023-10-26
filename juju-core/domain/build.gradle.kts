dependencies {
    implementation(project(":juju-infrastructure"))

    implementation(project(":juju-support:utils"))
    implementation(project(":juju-support:constants"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
}