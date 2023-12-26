package com.juloungjuloung.juju

import com.juloungjuloung.juju.repository.product.BraceletRepositoryImpl
import com.juloungjuloung.juju.repository.product.EarringRepositoryImpl
import com.juloungjuloung.juju.repository.product.NecklaceRepositoryImpl
import com.juloungjuloung.juju.repository.product.ProductRepositoryImpl
import com.juloungjuloung.juju.repository.product.RingRepositoryImpl
import com.juloungjuloung.juju.repository.product.color.ProductColorRepositoryImpl
import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

/**
 * <div> SpringBootApplication : 멀티모듈 환경에서의 통합 테스트 위한 어노테이션 </div>
 * <p> TestConfiguration : Configuration 기능과 하위 빈들은 테스트 스코프에서만 동작하도록 도와주는 TestConfiguration 어노테이션 </p>
 */
@SpringBootApplication
@TestConfiguration
@Import(
    value = [
        ProductRepositoryImpl::class, BraceletRepositoryImpl::class, EarringRepositoryImpl::class,
        NecklaceRepositoryImpl::class, RingRepositoryImpl::class, ProductColorRepositoryImpl::class
    ]
)
class TestConfiguration {

    @Bean
    fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }

    @Bean
    fun fixtureMonkey(): FixtureMonkey {
        return FixtureMonkey.builder()
            .plugin(KotlinPlugin())
            .build()
    }
}