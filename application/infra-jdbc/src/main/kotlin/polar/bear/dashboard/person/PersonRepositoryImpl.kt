package polar.bear.dashboard.person

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.domain.Role
import polar.bear.dashboard.person.infra.PersonRepository
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

class PersonRepositoryImpl(
    dataSource: DataSource
) : PersonRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun loadUserByUsername(username: String): Optional<PersonDetail> {
        val getUserDetails = """
           SELECT p.username, p.password, p.active, r.name
           FROM person p
               JOIN person_roles pr on p.id = pr.person_id
               JOIN roles r on r.id = pr.role_id
           WHERE p.username = ?;
        """.trimIndent()

        return try {

            val result = jdbcTemplate.queryForObject(
                getUserDetails,
                personDetailsMapper(),
                username
            )

            return Optional.ofNullable(result)

        } catch (exception: DataAccessException) {
            Optional.empty()
        }
    }

    private fun personDetailsMapper(): RowMapper<PersonDetail> {
        val finalPerson = PersonDetail()
        val currentUserRoles: MutableList<Role> = mutableListOf()

        return RowMapper<PersonDetail> { rs: ResultSet, _: Int ->
            while (rs.next()) {
                val username = rs.getString("username")
                val password = rs.getString("password")
                val active = rs.getBoolean("active")
                if (finalPerson.username.isBlank()) {
                    PersonDetail(
                        username = username,
                        password = password,
                        isActive = active
                    )
                    currentUserRoles.add(Role.valueOf(rs.getString("name")))
                }
                currentUserRoles.add(Role.valueOf(rs.getString("name")))
            }

            print("FOUND USER:")
            println(finalPerson.toString())

            finalPerson

        }
    }

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