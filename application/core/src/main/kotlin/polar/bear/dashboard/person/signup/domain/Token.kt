package polar.bear.dashboard.person.signup.domain

import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID

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
                expirationDate = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES)),
                personId = personId
            )
        }
    }
}
