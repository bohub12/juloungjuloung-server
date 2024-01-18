package com.juloungjuloung.juju

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

/**
 * SpringBootApplication : 멀티모듈 환경에서의 통합 테스트 위한 어노테이션
 *
 * TestConfiguration : Configuration 기능과 하위 빈들은 테스트 스코프에서만 동작하도록 도와주는 TestConfiguration 어노테이션
 */
@SpringBootApplication
@TestConfiguration
class TestConfiguration(
    @PersistenceContext
    private val entityManager: EntityManager
) {

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}