package polar.bear.dashboard.person.token

import javax.sql.DataSource
import org.springframework.jdbc.core.JdbcTemplate
import polar.bear.dashboard.person.verifytoken.domain.UpdateToken
import polar.bear.dashboard.person.verifytoken.infra.UpdateTokenRepository

class UpdateTokenRepositoryImpl(
    dataSource: DataSource
) : UpdateTokenRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun updateToken(updateToken: UpdateToken): Boolean {
        val updateQuery = """
            update token
            set token = :newToken,
                expiry_date = :newExpirationDate
            where id = :tokenId
        """.trimIndent()

        val paramsMap = mapOf(
            "newToken" to updateToken.newToken,
            "newExpirationDate" to updateToken.newExpirationDate,
            "tokenId" to updateToken.tokenId.value
        )

        val success = jdbcTemplate.update(updateQuery, paramsMap)
        return success > 0
    }
}