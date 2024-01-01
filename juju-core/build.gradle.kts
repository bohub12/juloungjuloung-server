plugins {
    `java-test-fixtures`
}

val fixtureMonkeyVersion: String by project

dependencies {
    runtimeOnly(project(":juju-infrastructure"))

    implementation(project(":juju-support:aws"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    testFixturesImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:$fixtureMonkeyVersion")
    testFixturesImplementation(project(":juju-support:constant"))
}