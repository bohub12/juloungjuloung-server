rootProject.name = "juju"

include(
    ":juju-api",
    ":juju-admin",

    ":juju-core",
    ":juju-infrastructure",
    ":juju-support:aws",
    ":juju-support:constant",
    ":juju-support:logging:admin-logging",
    ":juju-support:logging:public-logging"
)

pluginManagement {
    val kotlinVersion: String by settings
    val kaptVersion: String by settings
    val ktLintVersion: String by settings

    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kaptVersion)
                "org.jlleitschuh.gradle.ktlint" -> useVersion(ktLintVersion)

                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
            }
        }
    }
}