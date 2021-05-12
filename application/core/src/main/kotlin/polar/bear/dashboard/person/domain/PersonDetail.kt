package polar.bear.dashboard.person.domain

data class PersonDetail(
    val username: String = "",
    val password: String = "",
    val isActive: Boolean = false,
    val role: MutableCollection<Role> = mutableListOf()
)
