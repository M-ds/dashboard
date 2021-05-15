package polar.bear.dashboard.person.domain

data class SignInPerson(
    val token: String,
    val username: String,
    val roles: List<String>
)
