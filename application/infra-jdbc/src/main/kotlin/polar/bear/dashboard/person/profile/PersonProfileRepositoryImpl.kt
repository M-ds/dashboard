package polar.bear.dashboard.person.profile

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import java.util.Optional
import java.util.UUID
import javax.sql.DataSource
import polar.bear.dashboard.person.PersonRowMapperUtil
import polar.bear.dashboard.person.profile.domain.PersonProfile
import polar.bear.dashboard.person.profile.infra.PersonProfileRepository

class PersonProfileRepositoryImpl(
    dataSource: DataSource
) : PersonProfileRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun getPersonProfile(personId: UUID): Optional<PersonProfile> {
        val getUserProfileQuery = """
            SELECT username, password, email
            FROM person p
            WHERE p.id = ?
        """.trimIndent()

        return try {
            val result = jdbcTemplate.queryForObject(
                getUserProfileQuery,
                PersonRowMapperUtil.userProfileRowMapper(),
                personId
            )

            return Optional.ofNullable(result)

        } catch (exception: DataAccessException) {
            Optional.empty()
        }
    }
}