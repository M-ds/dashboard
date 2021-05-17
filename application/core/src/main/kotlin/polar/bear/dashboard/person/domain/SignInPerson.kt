package polar.bear.dashboard.person.domain

data class SignInPerson(
    val id: Int,
    val token: String,
    val username: String,
    val roles: List<String>
)
