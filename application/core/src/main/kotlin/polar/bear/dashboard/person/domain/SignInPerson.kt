package polar.bear.dashboard.person.domain

import java.util.UUID

data class SignInPerson(
    val id: UUID,
    val token: String,
    val username: String,
    val roles: List<String>
)
