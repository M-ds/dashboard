package polar.bear.dashboard.person.auth.domain

import java.time.LocalDate
import java.util.UUID

data class Person(
    val id: UUID,
    val username: String,
    val email: String,
    val password: String,
    val active: Boolean,
    val creationDate: LocalDate,
    val role: UUID,
    val tokenId: UUID
)
