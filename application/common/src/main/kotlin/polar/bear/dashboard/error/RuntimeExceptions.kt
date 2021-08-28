package polar.bear.dashboard.error

data class CouldNotInsertException(override val message: String) : RuntimeException(message)
data class CouldNotInsertPersonException(override val message: String) : RuntimeException(message)
data class CouldNotInsertRoleForPersonException(override val message: String) : RuntimeException(message)