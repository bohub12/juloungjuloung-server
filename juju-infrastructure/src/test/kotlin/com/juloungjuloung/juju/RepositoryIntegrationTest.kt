package com.juloungjuloung.juju

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import kotlin.reflect.KClass

@DataJpaTest
@ActiveProfiles("test")
@Import(RepositoryImportSelector::class)
annotation class RepositoryIntegrationTest(
    vararg val value: KClass<*>
)