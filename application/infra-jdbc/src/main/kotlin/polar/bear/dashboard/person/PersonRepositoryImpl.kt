package polar.bear.dashboard.person

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.infra.PersonRepository
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

class PersonRepositoryImpl(
    dataSource: DataSource
) : PersonRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun getPersonProfile(personId: Int): Optional<PersonProfile> {
        val getUserProfileQuery = """
            SELECT username, password, email
            FROM person p
            WHERE p.id = ?
        """.trimIndent()

        return try {
            val result = jdbcTemplate.queryForObject(
                getUserProfileQuery,
                userProfileRowMapper(),
                personId
            )

            return Optional.ofNullable(result)

        } catch (exception: DataAccessException) {
            Optional.empty()
        }
    }

    private fun userProfileRowMapper(): RowMapper<PersonProfile> {
        return RowMapper<PersonProfile> { rs: ResultSet, _: Int ->
            PersonProfile(
                userName = rs.getString("username"),
                password = rs.getString("password"),
                email = rs.getString("email")
            )
        }
    }
}