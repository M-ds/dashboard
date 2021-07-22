package polar.bear.dashboard.person.auth.domain

data class PersonDetail(
    var username: String = "",
    var password: String = "",
    var isActive: Boolean = false,
    var roles: MutableCollection<Role> = mutableListOf()
)
