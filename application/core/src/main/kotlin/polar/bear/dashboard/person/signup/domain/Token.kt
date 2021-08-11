package polar.bear.dashboard.person.signup.domain

import java.time.LocalDateTime
import java.util.UUID
import polar.bear.dashboard.util.localdate.LocalDateTimeUtil

data class Token(
    val id: UUID,
    val token: UUID,
    val expirationDate: LocalDateTime,
    val personId: UUID
) {
    companion object {
        fun createToken(personId: UUID): Token {
            return Token(
                id = UUID.randomUUID(),
                token = UUID.randomUUID(),
                expirationDate = LocalDateTimeUtil.createExpirationDate(10),
                personId = personId
            )
        }
    }
}
