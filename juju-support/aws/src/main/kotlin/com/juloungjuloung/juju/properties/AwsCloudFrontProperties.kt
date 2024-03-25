package com.juloungjuloung.juju.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws.cloudfront")
data class AwsCloudFrontProperties(
    val imageDistributionDomain: String
)