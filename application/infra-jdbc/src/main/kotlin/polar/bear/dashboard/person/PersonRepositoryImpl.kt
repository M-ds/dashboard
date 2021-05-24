package polar.bear.dashboard.person

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import polar.bear.dashboard.person.domain.Person
import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.domain.Role
import polar.bear.dashboard.person.infra.PersonRepository
import java.sql.ResultSet
import java.util.Optional
import java.util.UUID
import javax.sql.DataSource

class PersonRepositoryImpl(
    dataSource: DataSource
) : PersonRepository {

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
                personDetailsMapper(),
                username
            )

            return Optional.ofNullable(result)

        } catch (exception: DataAccessException) {
            Optional.empty()
        }
    }

    //TODO: make it better..
    private fun personDetailsMapper(): RowMapper<PersonDetail> {
        val finalPerson = PersonDetail()
        val currentUserRoles: MutableList<Role> = mutableListOf()

        return RowMapper<PersonDetail> { rs: ResultSet, _: Int ->
            do {
                val username = rs.getString("username")
                val password = rs.getString("password")
                val active = rs.getBoolean("active")
                if (finalPerson.username.isBlank()) {
                    finalPerson.username = username
                    finalPerson.password = password
                    finalPerson.isActive = active
                }
                currentUserRoles.add(Role.valueOf(rs.getString("name")))

            } while (rs.next())

            finalPerson.roles = currentUserRoles
            finalPerson

        }
    }

    override fun usernameExists(username: String): Boolean {
        val usernameExistsQuery = """
            SELECT COUNT(1)
            FROM person p
            WHERE p.username = ?;
        """.trimIndent()

        val result = jdbcTemplate.queryForObject(
            usernameExistsQuery,
            Int::class.java,
            username
        )
        return result > 0
    }

    override fun emailExits(email: String): Boolean {
        val emailExistsQuery = """
            SELECT COUNT(1)
            FROM person p
            WHERE p.email = ?;
        """.trimIndent()

        val result = jdbcTemplate.queryForObject(
            emailExistsQuery,
            Int::class.java,
            email
        )

        return result > 0
    }

    override fun getPersonIdFromUsername(username: String): UUID {
        val getPersonId = """
            SELECT id
            FROM person p
            WHERE p.username = ?;
        """.trimIndent()

        return jdbcTemplate.queryForObject(
            getPersonId,
            UUID::class.java,
            username
        )
    }

    override fun getPersonProfile(personId: UUID): Optional<PersonProfile> {
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

    override fun save(person: Person): Boolean {
        val personParams = mutableMapOf<String, Any>()
        personParams["id"] = person.id
        personParams["username"] = person.username
        personParams["email"] = person.email
        personParams["password"] = person.password
        personParams["token"] = person.token
        personParams["active"] = person.active
        personParams["creation_date"] = person.creationDate

        val roleParams = mutableMapOf<String, Any>()
        roleParams["id"] = person.id
        roleParams["role"] = person.role


        try {
            val affectedRows = insertValue(personParams, "person")
            if (affectedRows <= 0) {
                return false
            }

            val updatedRows = insertValue(roleParams, "person_role")
            if (updatedRows <= 0) {
                return false
            }

        } catch (ex: Exception) {
            print(ex)
            return false
        }
        return true
    }

    private fun insertValue(params: MutableMap<String, Any>, tableName: String): Int {
        return SimpleJdbcInsert(jdbcTemplate)
            .withTableName(tableName)
            .execute(MapSqlParameterSource(params))
    }

    override fun getRoleId(role: Role): UUID {
        val getPersonId = """
            SELECT id
            FROM role r
            WHERE r.name = ?;
        """.trimIndent()

        return jdbcTemplate.queryForObject(
            getPersonId,
            UUID::class.java,
            role.name
        )
    }
}
