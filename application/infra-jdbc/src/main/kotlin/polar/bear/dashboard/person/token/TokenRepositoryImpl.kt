package polar.bear.dashboard.person.token

import java.lang.RuntimeException
import java.util.UUID
import javax.sql.DataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import polar.bear.dashboard.person.signup.domain.Token
import polar.bear.dashboard.person.signup.infra.TokenRepository

class TokenRepositoryImpl(
    dataSource: DataSource
) : TokenRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun saveToken(token: Token): Boolean {
        try {
            val tokenParams = createTokenInsertMap(token)
            val affectedRows = SimpleJdbcInsert(jdbcTemplate)
                .withTableName("token")
                .execute(MapSqlParameterSource(tokenParams))

            if (affectedRows != 1) {
                // TODO: Create a different exception
                throw RuntimeException("Could not insert Person!")
            }
        } catch (error: Exception) {
            return false
        }
        return true
    }

    private fun createTokenInsertMap(token: Token): MutableMap<String, Any> {
        val insertMap = mutableMapOf<String, Any>()
        insertMap["id"] = token.id
        insertMap["token"] = token.token
        insertMap["expiry_date"] = token.expirationDate
        insertMap["person_id"] = token.personId
        return insertMap
    }
}