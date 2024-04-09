package com.juloungjuloung.juju.config

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableEncryptableProperties
class JasyptConfig

class CustomEncryptedPropertyDetector : EncryptablePropertyDetector {
    override fun isEncrypted(value: String): Boolean {
        return value.startsWith("ENC(") && value.endsWith(")")
    }

    override fun unwrapEncryptedValue(value: String): String {
        return value.substring("ENC(".length).removeSuffix(")")
    }
}