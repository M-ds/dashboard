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
            set token = '${updateToken.newToken}',
                expiry_date = '${updateToken.newExpirationDate}'
            where id = '${updateToken.tokenId.value}'
        """.trimIndent()

        val success = jdbcTemplate.update(updateQuery)
        return success > 0
    }
}