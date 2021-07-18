package polar.bear.dashboard.person.domain

import java.time.LocalDate
import java.util.UUID

data class Person(
    val id: UUID,
    val username: String,
    val email: String,
    val password: String,
    val token: UUID,
    val active: Boolean,
    val creationDate: LocalDate,
    val role: UUID
)
