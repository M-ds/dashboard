package polar.bear.dashboard.person.auth

import java.util.Optional
import javax.sql.DataSource
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import polar.bear.dashboard.person.PersonRowMapperUtil
import polar.bear.dashboard.person.auth.domain.PersonDetail
import polar.bear.dashboard.person.auth.infra.AuthenticationRepository

class AuthenticationRepositoryImpl(
    dataSource: DataSource
) : AuthenticationRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun loadUserByUsername(username: String): Optional<PersonDetail> {
        val getUserDetails = """
            SELECT p.username, p.password, p.active, r.name
            FROM person p
                     JOIN person_role pr ON p.id = pr.person_id
                     JOIN role r ON r.id = pr.role_id
            WHERE p.username = ?;
        """.trimIndent()

        return try {

            val result = jdbcTemplate.queryForObject(
                getUserDetails,
                PersonRowMapperUtil.personDetailsMapper(),
                username
            )

            return Optional.ofNullable(result)

        } catch (exception: DataAccessException) {
            Optional.empty()
        }
    }
}