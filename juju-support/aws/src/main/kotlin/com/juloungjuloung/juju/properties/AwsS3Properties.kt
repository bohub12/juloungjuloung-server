package com.juloungjuloung.juju.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws.s3")
data class AwsS3Properties(
    val bucket: String
)