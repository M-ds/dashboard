package polar.bear.dashboard.config.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import polar.bear.dashboard.person.PersonRepositoryImpl
import polar.bear.dashboard.person.infra.PersonRepository
import javax.sql.DataSource
import polar.bear.dashboard.person.signup.infra.TokenRepository
import polar.bear.dashboard.person.token.TokenRepositoryImpl

@Configuration
open class RepositoryConfig {

    @Value("\${spring.datasource.url}")
    private val url: String = ""

    @Value("\${spring.datasource.username}")
    private val username: String = ""

    @Value("\${spring.datasource.password}")
    private val password: String = ""

    @Bean
    open fun initUserRepository(): PersonRepository {
        return PersonRepositoryImpl(createDataSource())
    }

    @Bean
    open fun initTokenRepository(): TokenRepository {
        return TokenRepositoryImpl(createDataSource())
    }

    private fun createDataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .build()
    }
}