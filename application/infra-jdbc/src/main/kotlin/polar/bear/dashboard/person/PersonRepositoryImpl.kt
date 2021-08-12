package polar.bear.dashboard.person

import java.util.UUID
import javax.sql.DataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.transaction.annotation.Transactional
import polar.bear.dashboard.person.auth.domain.Person
import polar.bear.dashboard.person.auth.domain.Role
import polar.bear.dashboard.person.domain.TokenId
import polar.bear.dashboard.person.infra.PersonRepository
import polar.bear.dashboard.person.verifytoken.domain.PersonRegisteredSuccess

open class PersonRepositoryImpl(
    dataSource: DataSource
) : PersonRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

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

    override fun getUsernameAndEmail(tokenId: TokenId): PersonRepository.UsernameAndEmailResponse {
        val query = """
            select p.username,
                   p.email
            from person p 
            where p.token_id = ?
        """.trimIndent()

        return jdbcTemplate.queryForObject(
            query,
            toUsernameAndEmailResponse,
            tokenId.value
        )!!
    }

    private val toUsernameAndEmailResponse: RowMapper<PersonRepository.UsernameAndEmailResponse> =
        RowMapper { rs, _ ->
            PersonRepository.UsernameAndEmailResponse(
                username = rs.getString("username"),
                email = rs.getString("email")
            )
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

    // TODO: check if this really works in a transaction like this.
    @Transactional
    override fun save(person: Person): Boolean {
        try {
            val personParams = createPersonParamMap(person)
            val roleParams = createPersonRoleParamMap(person)

            val affectedRows = insertValue(personParams, "person")
            if (affectedRows != 1) {
                throw RuntimeException("Could not update person!")
            }

            val updatedRows = insertValue(roleParams, "person_role")
            if (updatedRows != 1) {
                throw RuntimeException("Could not update person_role!")
            }
        } catch (error: Exception) {
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

    override fun successfulRegistered(personRegistered: PersonRegisteredSuccess) {
        val deleteQuery = "delete from token where person_id = '${personRegistered.personId}'"
        val updatePersonQuery = """
            update person
            set token_id = null,
                active   = true
            where id = '${personRegistered.personId}'
        """.trimIndent()

        jdbcTemplate.update(deleteQuery)
        jdbcTemplate.update(updatePersonQuery)
    }
}
