package polar.bear.dashboard.exception

data class CouldNotInsertException(override val message: String) : RuntimeException(message)