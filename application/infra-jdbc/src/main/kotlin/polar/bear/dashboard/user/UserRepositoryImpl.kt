package polar.bear.dashboard.user

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import polar.bear.dashboard.user.domain.UserProfile
import polar.bear.dashboard.user.infra.UserRepository
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

class UserRepositoryImpl(
    dataSource: DataSource
) : UserRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun getUserProfile(userId: Int): Optional<UserProfile> {
        val getUserProfileQuery = """
            SELECT username, password, email
            FROM User u
            WHERE u.id = ?
        """.trimIndent()

        return try {
            val result = jdbcTemplate.queryForObject(
                getUserProfileQuery,
                userProfileRowMapper(),
                userId
            )

            Optional.ofNullable(result)

        } catch (exception: DataAccessException) {
            Optional.empty()
        }
    }

    private fun userProfileRowMapper(): RowMapper<UserProfile> {
        return RowMapper<UserProfile> { rs: ResultSet, _: Int ->
            UserProfile(
                userName = rs.getString("username"),
                password = rs.getString("password"),
                email = rs.getString("email")
            )
        }
    }
}