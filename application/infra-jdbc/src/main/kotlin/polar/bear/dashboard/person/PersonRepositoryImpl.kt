package polar.bear.dashboard.person

import java.lang.RuntimeException
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import polar.bear.dashboard.person.PersonRowMapperUtil.Companion.personDetailsMapper
import polar.bear.dashboard.person.PersonRowMapperUtil.Companion.userProfileRowMapper
import polar.bear.dashboard.person.domain.Person
import polar.bear.dashboard.person.domain.PersonDetail
import polar.bear.dashboard.person.domain.PersonProfile
import polar.bear.dashboard.person.domain.Role
import polar.bear.dashboard.person.infra.PersonRepository
import java.util.Optional
import java.util.UUID
import javax.sql.DataSource
import org.springframework.transaction.annotation.Transactional

open class PersonRepositoryImpl(
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

    // TODO: check if this really works in a transaction like this.
    @Transactional
    override fun save(person: Person): Boolean {
        try {
            val personParams = createPersonParamMap(person)
            val roleParams = createPersonRoleParamMap(person)

            val affectedRows = insertValue(personParams, "person")
            if (affectedRows <= 0) {
                throw RuntimeException("Could not update person!")
            }

            val updatedRows = insertValue(roleParams, "person_role")
            if (updatedRows <= 0) {
                throw RuntimeException("Could not update person_role!")
            }
        } catch (error: Exception){
            return false
        }

        return true
    }

    private fun createPersonParamMap(person: Person): MutableMap<String, Any> {
        val personParams = mutableMapOf<String, Any>()
        personParams["id"] = person.id
        personParams["username"] = person.username
        personParams["email"] = person.email
        personParams["password"] = person.password
        personParams["active"] = person.active
        personParams["creation_date"] = person.creationDate
        personParams["token_id"] = person.tokenId
        return personParams
    }

    private fun createPersonRoleParamMap(person: Person): MutableMap<String, Any> {
        val roleParams = mutableMapOf<String, Any>()
        roleParams["person_id"] = person.id
        roleParams["role_id"] = person.role
        return roleParams
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
