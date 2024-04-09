package com.juloungjuloung.juju.config.typeconverter

import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

class StringToEnumConverterFactory : ConverterFactory<String, Enum<*>> {
    class StringToEnumConverter<T : Enum<*>>(
        private val enumClass: Class<T>
    ) : Converter<String, T> {
        override fun convert(source: String): T {
            if (source.isEmpty()) {
                throw IllegalArgumentException()
            }

            return enumClass.enumConstants.first { it!!.name == source.trim() }
        }
    }

    override fun <E : Enum<*>> getConverter(clazz: Class<E>): Converter<String, E> {
        return StringToEnumConverter(clazz)
    }
}