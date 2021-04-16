package polar.bear.dashboard.user

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import polar.bear.dashboard.user.domain.UserProfile
import polar.bear.dashboard.user.infra.UserRepository
import javax.sql.DataSource

@Repository
class UserRepositoryImpl(
    private val dataSource: DataSource
) : UserRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun getUserProfile(userId: Int): UserProfile {
        TODO("Not yet implemented")
    }
}