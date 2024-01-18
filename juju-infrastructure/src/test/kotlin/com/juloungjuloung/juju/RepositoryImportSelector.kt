package com.juloungjuloung.juju

import org.springframework.context.annotation.ImportSelector
import org.springframework.core.type.AnnotationMetadata

class RepositoryImportSelector : ImportSelector {

    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        val repositoryClass = importingClassMetadata.getAnnotationAttributes(
            RepositoryIntegrationTest::class.qualifiedName!!
        )?.get("value") as Class<*>

        return arrayOf(repositoryClass.name)
    }
}