package com.juloungjuloung.juju.config

import com.juloungjuloung.juju.config.typeconverter.StringToEnumConverterFactory
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverterFactory(StringToEnumConverterFactory())
    }
}