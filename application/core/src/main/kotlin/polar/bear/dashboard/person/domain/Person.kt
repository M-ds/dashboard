package polar.bear.dashboard.person.domain

data class Person(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val role: MutableCollection<Role> = mutableListOf()
)
