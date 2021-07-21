package polar.bear.dashboard.person.verifytoken.domain

import java.time.LocalDateTime
import java.util.UUID

data class RegisteredPerson(
    val personId: UUID,
    val active: Boolean,
    val tokenId: UUID,
    val expirationDate: LocalDateTime
)
