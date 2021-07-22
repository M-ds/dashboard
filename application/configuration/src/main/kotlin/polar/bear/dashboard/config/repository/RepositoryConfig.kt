package polar.bear.dashboard.config.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import polar.bear.dashboard.person.PersonRepositoryImpl
import polar.bear.dashboard.person.infra.PersonRepository
import javax.sql.DataSource
import polar.bear.dashboard.person.profile.PersonProfileRepositoryImpl
import polar.bear.dashboard.person.profile.infra.PersonProfileRepository
import polar.bear.dashboard.person.signup.infra.TokenRepository
import polar.bear.dashboard.person.token.RetrieveTokenRepositoryImpl
import polar.bear.dashboard.person.token.TokenRepositoryImpl
import polar.bear.dashboard.person.verifytoken.infra.RetrieveTokenRepository

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

    @Bean
    open fun initRetrieveTokenRepository(): RetrieveTokenRepository {
        return RetrieveTokenRepositoryImpl(createDataSource())
    }

    @Bean
    open fun initPersonProfileRepository(): PersonProfileRepository {
        return PersonProfileRepositoryImpl(createDataSource())
    }

    private fun createDataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .build()
    }
}