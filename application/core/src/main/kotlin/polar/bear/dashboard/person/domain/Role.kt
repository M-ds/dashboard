package polar.bear.dashboard.person.domain

enum class Role {
    ROLE_USER,
    ROLE_MEMBER,
    ROLE_ADMIN;

    companion object {
        fun convertToRole(role: String): Role {
            return when (role) {
                "USER" -> ROLE_USER
                "MEMBER" -> ROLE_MEMBER
                "ADMIN" -> ROLE_ADMIN
                else -> ROLE_USER
            }
        }
    }
}

enum class SecurityRole {
    USER,
    MEMBER,
    ADMIN
}