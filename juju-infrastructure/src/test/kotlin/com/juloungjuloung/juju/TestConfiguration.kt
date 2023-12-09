package com.juloungjuloung.juju

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

/**
 * <div> SpringBootApplication : 멀티모듈 환경에서의 통합 테스트 위한 어노테이션 </div>
 * <p> TestConfiguration : Configuration 기능과 하위 빈들은 테스트 스코프에서만 동작하도록 도와주는 TestConfiguration 어노테이션 </p>
 */
@SpringBootApplication
@TestConfiguration
class TestConfiguration {

    @Bean
    fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}