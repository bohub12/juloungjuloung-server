package com.juloungjuloung.juju.properties

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AwsS3Properties::class)
class AwsPropertiesConfig