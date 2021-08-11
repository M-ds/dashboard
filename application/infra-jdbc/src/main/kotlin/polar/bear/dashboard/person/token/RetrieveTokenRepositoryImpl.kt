package polar.bear.dashboard.person.token

import java.sql.ResultSet
import java.util.UUID
import javax.sql.DataSource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import polar.bear.dashboard.person.verifytoken.domain.RegisteredPerson
import polar.bear.dashboard.person.verifytoken.infra.RetrieveTokenRepository

class RetrieveTokenRepositoryImpl(
    dataSource: DataSource
) : RetrieveTokenRepository {

    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun retrieveToken(tokenId: UUID): RegisteredPerson {
        val query = """
            select p.id as personId, 
                   p.active, 
                   t.id as tokenId, 
                   t.expiry_date
            from token t
                join person p on p.id = t.person_id
            where t.token = ?;
        """.trimIndent()

        return jdbcTemplate.queryForObject(
            query,
            registeredPersonRowMapper(),
            tokenId
        )!!

    }

    private fun registeredPersonRowMapper(): RowMapper<RegisteredPerson> {
        return RowMapper<RegisteredPerson> { rs: ResultSet, _: Int ->
            RegisteredPerson(
                personId = UUID.fromString(rs.getString("personId")),
                active = rs.getBoolean("active"),
                tokenId = UUID.fromString(rs.getString("tokenId")),
                expirationDate = rs.getTimestamp("expiry_date").toLocalDateTime(),
            )
        }
    }
}