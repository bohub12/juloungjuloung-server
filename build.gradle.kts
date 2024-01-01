import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management") apply false
    id("org.jlleitschuh.gradle.ktlint")
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    kotlin("plugin.jpa") apply false
    kotlin("kapt") apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks {
    bootJar {
        enabled = false
    }
    jar {
        enabled = true
    }
}

val projectGroup: String by project
val applicationVersion: String by project

allprojects {
    group = projectGroup
    version = applicationVersion
    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jetbrains.kotlin.kapt")

    tasks {
        bootJar {
            enabled = false
        }
        jar {
            enabled = true
        }
    }

    val kotestVersion: String by project
    val kotestSpringExtensionVersion: String by project
    val mockkVersion: String by project
    val testContainerVersion: String by project
    val fixtureMonkeyVersion: String by project
//    val kotestTestContainerVersion: String by project

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
        testImplementation("io.kotest:kotest-property:$kotestVersion")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:$kotestSpringExtensionVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")
        testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:$fixtureMonkeyVersion")

        // testcontainers bom (auto configure specific version)
        implementation(platform("org.testcontainers:testcontainers-bom:$testContainerVersion"))
        testImplementation("org.testcontainers:mysql")
        testImplementation("org.testcontainers:junit-jupiter")

        // constant (enum, exception etc ..)
        if (project.name != "constant") {
            implementation(project(":juju-support:constant"))
        }
    }
}