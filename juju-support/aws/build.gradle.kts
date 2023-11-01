val springCloudAwsVersion: String by project

dependencies {
    // spring-cloud-aws 관련 라이브러리 버전 관리
    implementation(platform("io.awspring.cloud:spring-cloud-aws-dependencies:$springCloudAwsVersion"))

    // aws - autoconfigure & s3
    implementation("io.awspring.cloud:spring-cloud-aws-starter-s3")
}