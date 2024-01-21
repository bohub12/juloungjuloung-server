package com.juloungjuloung.juju

import org.springframework.context.annotation.ImportSelector
import org.springframework.core.type.AnnotationMetadata

class RepositoryImportSelector : ImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        val repositoryClasses = importingClassMetadata.getAnnotationAttributes(
            RepositoryIntegrationTest::class.qualifiedName!!
        )?.get("value") as Array<Class<*>>

        return repositoryClasses.map { it.name }.toTypedArray()
    }
}