dependencies {
    implementation(project(":juju-core:domain"))
    runtimeOnly(project(":juju-infrastructure"))

    implementation(project(":juju-support:aws"))
    implementation(project(":juju-support:utils"))
    implementation(project(":juju-support:constants"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
//    implementation("jakarta.transaction:jakarta.transaction-api")
}