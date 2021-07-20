package polar.bear.dashboard.person.signup.request

data class SignupRequest(
    val username: String,
    val email: String,
    val password: String,
    val passwordConformation: String
)
