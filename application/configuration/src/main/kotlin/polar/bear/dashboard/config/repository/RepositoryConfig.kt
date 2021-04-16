package polar.bear.dashboard.config.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import polar.bear.dashboard.user.UserRepositoryImpl
import polar.bear.dashboard.user.infra.UserRepository
import javax.sql.DataSource

@Configuration
open class RepositoryConfig {

    @Value("\${spring.datasource.url}")
    private val url: String = ""

    @Value("\${spring.datasource.username}")
    private val username: String = ""

    @Value("\${spring.datasource.password}")
    private val password: String = ""

    @Bean
    open fun userRepository(): UserRepository {
        return UserRepositoryImpl(createDataSource())
    }

    private fun createDataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .build()
    }
}